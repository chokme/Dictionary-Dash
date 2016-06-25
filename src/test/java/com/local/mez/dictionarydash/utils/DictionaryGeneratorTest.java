package com.local.mez.dictionarydash.utils;

import com.local.mez.dictionarydash.domain.Dictionary;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.*;

import static org.junit.Assert.*;

public class DictionaryGeneratorTest {

    private String testCsvFile = "./resources/dictionaryTest.csv";
    private Set<String> words;

    @Before
    public void setUp() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(testCsvFile).getFile());
        words = CsvFileReader.parse(file);
    }

    @Test
    public void generateDictionary(){
        Map<Long, Dictionary> dictionaryMap = DictionaryGenerator.sortedDictionaryBySize(words);
        assertNotNull("size 3 exists", dictionaryMap.get(3L));
        assertEquals("no. words size 3", 9, dictionaryMap.get(3L).getWords().size());
        assertNotNull("size 4 exists", dictionaryMap.get(4L));
        assertEquals("no. words size 4", 9, dictionaryMap.get(4L).getWords().size());
        assertNotNull("size 5 exists", dictionaryMap.get(5L));
        assertEquals("no. words size 5", 1, dictionaryMap.get(5L).getWords().size());
        assertNotNull("size 10 exists", dictionaryMap.get(10L));
        assertEquals("no. words size 10", 1, dictionaryMap.get(10L).getWords().size());

        assertNull("size 11 doesn't exists", dictionaryMap.get(11L));
    }

    @Test
    public void emptyListPassedIn() {
        Set<String> empty = new HashSet();
        Map<Long, Dictionary> dictionaryMap = DictionaryGenerator.sortedDictionaryBySize(empty);

        assertTrue("empty map", dictionaryMap.isEmpty());
    }

    @Test
    public void nullListPassedIn() {
        Map<Long, Dictionary> dictionaryMap = DictionaryGenerator.sortedDictionaryBySize(null);

        assertTrue("empty map", dictionaryMap.isEmpty());
    }




}
