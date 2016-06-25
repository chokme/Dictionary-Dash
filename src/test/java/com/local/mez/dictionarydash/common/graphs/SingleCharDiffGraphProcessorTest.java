package com.local.mez.dictionarydash.common.graphs;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SingleCharDiffGraphProcessorTest {

    private SingleCharDiffGraphProcessor singleCharDiffGraphProcessor = new SingleCharDiffGraphProcessor();

    @Test
    public void firstStringIsNull() {
        assertFalse(singleCharDiffGraphProcessor.linkVertices(null, ""));
    }

    @Test
    public void secondStringIsNull() {
        assertFalse(singleCharDiffGraphProcessor.linkVertices(null, ""));
    }

    @Test
    public void firstStringIsEmpty() {
        assertFalse(singleCharDiffGraphProcessor.linkVertices("", "asf"));
    }

    @Test
    public void secondStringIsEmpty() {
        assertFalse(singleCharDiffGraphProcessor.linkVertices("some", " "));
    }

    @Test
    public void stringLengthsAreDifferent() {
        assertFalse(singleCharDiffGraphProcessor.linkVertices("lets", "go"));
    }

    @Test
    public void stringsHaveMoreThanOneCharDiff() {
        assertFalse(singleCharDiffGraphProcessor.linkVertices("job", "jim"));
    }

    @Test
    public void stringsHasOneCharDiffButNotSameSequence() {
        assertFalse(singleCharDiffGraphProcessor.linkVertices("min", "jim"));
    }

    @Test
    public void stringsHaveOneCharDiffOnlyInSequence() {
        assertTrue(singleCharDiffGraphProcessor.linkVertices("Job", "Bob"));
    }

    @Test
    public void stringsHaveOneCharDiffIgnoreCase() {
        assertTrue(singleCharDiffGraphProcessor.linkVertices("JoB", "Bob"));
    }
}
