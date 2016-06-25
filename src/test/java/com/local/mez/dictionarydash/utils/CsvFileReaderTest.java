package com.local.mez.dictionarydash.utils;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CsvFileReaderTest {

    private String testCsvFile = "./resources/csvFileReaderTest.csv";
    private File file;

    @Before
    public void setUp() {
        ClassLoader classLoader = getClass().getClassLoader();
        file = new File(classLoader.getResource(testCsvFile).getFile());
    }

    @Test(expected = NullPointerException.class)
    public void nullFile() {
        CsvFileReader.parse(null);
    }

    @Test
    public void ableToParseFileToSet() {
        Set<String> words = CsvFileReader.parse(file);
        assertEquals("expected size", 20, words.size());
    }

    @Test
    public void allWordsAreStoredInLowerCase() {
        Set<String> words = CsvFileReader.parse(file);
        for(String word : words) {
            assertTrue(word + " is lower case", word.equals(word.toLowerCase()));
        }
    }

    @Test
    public void noDuplicates() {
        Set<String> words = CsvFileReader.parse(file);
        int count = 0;
        for(String word : words) {
            if(word.equals("prune")) {
                count+=1;
            }
        }
        assertTrue("only 1 prune", count == 1);
    }

}
