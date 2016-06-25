package com.local.mez.dictionarydash;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Stack;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ShortestWordTransformationConfigurationTest {

    private String csvFile = "shortestWordTransformationTest.csv";
    private ShortestWordTransformationConfiguration processor;


    @Before
    public void setUp() {
        System.setProperty("dictionary.file", csvFile);
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ShortestWordTransformationConfiguration.class);
        processor = context.getBean(ShortestWordTransformationConfiguration.class);
        processor.loadDictionary();
    }

    @Test
    public void startStringIsBlank() {
        Stack<String> result = processor.getShortestWordTransformation("", "thing");
        assertTrue("result is empty", result.isEmpty());
    }

    @Test
    public void endStringIsBlank() {
        Stack<String> result = processor.getShortestWordTransformation("some", " ");
        assertTrue("result is empty", result.isEmpty());
    }

    @Test
    public void startAndEndAreNotSameLength() {
        Stack<String> result = processor.getShortestWordTransformation("something", "small");
        assertTrue("result is empty", result.isEmpty());
    }

    @Test
    public void noDictionaryAMtchingGivenWords() {
        Stack<String> result = processor.getShortestWordTransformation("something", "something");
        assertTrue("result is empty", result.isEmpty());
    }

    @Test
    public void startWordIsNotInTheDictionary() {
        Stack<String> result = processor.getShortestWordTransformation("boo", "fob");
        assertTrue("result is empty", result.isEmpty());
    }

    @Test
    public void endWordIsNotInTheDictionary() {
        Stack<String> result = processor.getShortestWordTransformation("hit", "rob");
        assertTrue("result is empty", result.isEmpty());
    }

    @Test
    public void startAndEndAreInTheDictionaryAndLink() {
        Stack<String> result = processor.getShortestWordTransformation("hit", "cog");
        assertTrue("result is not empty", !result.isEmpty());
        assertTrue("result size", result.size() == 5);

        Stack<String> expectedResult = getExpectedResult();

        assertEquals("results match", expectedResult.toString(), result.toString());
    }

    @Test
    public void startAndEndAreLinkIgnoreCase() {
        Stack<String> result = processor.getShortestWordTransformation("hIt", "COG");
        assertTrue("result is not empty", !result.isEmpty());
        assertTrue("result size", result.size() == 5);

        Stack<String> expectedResult = getExpectedResult();

        assertEquals("results match", expectedResult.toString(), result.toString());
    }

    private Stack<String> getExpectedResult() {
        Stack<String> expectedResult = new Stack();
        expectedResult.push("hit");
        expectedResult.push("hot");
        expectedResult.push("dot");
        expectedResult.push("dog");
        expectedResult.push("cog");

        return expectedResult;
    }


}
