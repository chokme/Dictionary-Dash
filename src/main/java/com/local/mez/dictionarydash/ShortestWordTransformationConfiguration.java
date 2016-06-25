package com.local.mez.dictionarydash;

import com.local.mez.dictionarydash.common.algorithms.DijkstrasAlgoConfiguration;
import com.local.mez.dictionarydash.common.algorithms.ShortestPath;
import com.local.mez.dictionarydash.common.graphs.GraphGenerator;
import com.local.mez.dictionarydash.common.graphs.GraphGeneratorConfiguration;
import com.local.mez.dictionarydash.domain.Dictionary;
import com.local.mez.dictionarydash.domain.Vertex;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.local.mez.dictionarydash.utils.CsvFileReader;
import com.local.mez.dictionarydash.utils.DictionaryGenerator;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.io.File;
import java.util.*;

@Configuration
@Import({GraphGeneratorConfiguration.class, DijkstrasAlgoConfiguration.class})
public class ShortestWordTransformationConfiguration {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Autowired
    private GraphGenerator graphGenerator;

    @Autowired
    private ShortestPath algorithm;

    @Value("${dictionary.file:dictionary.csv}")
    private String csvFile;

    private Set<String> csvAsList;
    private Map<Long, Dictionary> sortedDictionaryBySize;

    public void loadDictionary() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("./resources/"+csvFile).getFile());
        csvAsList = CsvFileReader.parse(file);

        if(csvAsList.isEmpty()) {
            throw new MissingResourceException("Resource didn't load", ShortestWordTransformationConfiguration.class.getName(), csvFile);
        }
        sortedDictionaryBySize = DictionaryGenerator.sortedDictionaryBySize(csvAsList);
        for(Dictionary d : sortedDictionaryBySize.values()) {

            Map<String, Vertex> graph = graphGenerator.create(d.getWords());
            d.setGraphMap(graph);
        }
    }

    public Stack<String> getShortestWordTransformation(String s1, String s2) {

        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        Stack<String> result = new Stack();
        Long wordLength = new Long(s1.length());
        Dictionary matchingDictionary = sortedDictionaryBySize.get(wordLength);

        if(!validateQuery(s1, s2, matchingDictionary)) {
            return result;
        }

        PriorityQueue<Vertex> priorityQueue = new PriorityQueue();
        priorityQueue.addAll(matchingDictionary.getGraphMap().values());

        result = algorithm.execute(matchingDictionary.getGraphMap().get(s1), matchingDictionary.getGraphMap().get(s2), priorityQueue);

        return result;

    }

    private boolean validateQuery(String s1, String s2, Dictionary matchingDictionary) {

        if(StringUtils.isBlank(s1) || StringUtils.isBlank(s2) || s1.length() != s2.length()) {
            System.out.println("start and/or end word is blank or they are not the same length");
            return false;
        }
        int wordLength = s1.length();
        if(matchingDictionary == null) {
            System.out.println("no dictionary with words of length " + wordLength + " was found");
            return false;
        }
        if(!matchingDictionary.getWords().contains(s1) || !matchingDictionary.getWords().contains(s2)) {
            System.out.println("start word or end word is not in the dictionary for length " + wordLength);
            return false;
        }

        return true;
    }

}
