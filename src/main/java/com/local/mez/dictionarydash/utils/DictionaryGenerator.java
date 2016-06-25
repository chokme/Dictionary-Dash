package com.local.mez.dictionarydash.utils;

import com.local.mez.dictionarydash.domain.Dictionary;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DictionaryGenerator {

    public static Map<Long, Dictionary> sortedDictionaryBySize(Set<String> words) {

        Map<Long, Dictionary> sortedDictionary = new HashMap();
        if(words == null || words.isEmpty()) {
            return sortedDictionary;
        }

        Dictionary dictionary;

        for(String word : words) {
            Long wordLength = new Long(word.length());
            dictionary = sortedDictionary.get(wordLength);
            if(dictionary == null) {
                dictionary = new Dictionary();
                sortedDictionary.put(wordLength, dictionary);
            }
            dictionary.addWord(word);
        }

        return sortedDictionary;
    }


}
