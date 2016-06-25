package com.local.mez.dictionarydash.domain;

import java.util.*;

public class Dictionary {

    private Set<String> words;
    private Map<String, Vertex> graphMap;

    public Set<String> getWords() {
        return words;
    }

    public void setWords(Set<String> words) {
        this.words = words;
    }

    public void addWord(String word) {
        if(this.getWords() == null) {
            Set<String> words = new HashSet();
            words.add(word);
            this.setWords(words);
        }
        else {
            this.getWords().add(word);
        }

    }

    public Map<String, Vertex> getGraphMap() {
        return graphMap;
    }

    public void setGraphMap(Map<String, Vertex> graphMap) {
        this.graphMap = graphMap;
    }
}
