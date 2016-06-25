package com.local.mez.dictionarydash.common.graphs;

import com.local.mez.dictionarydash.domain.Vertex;
import org.junit.Before;
import org.junit.Test;
import com.local.mez.dictionarydash.utils.CsvFileReader;

import java.io.File;
import java.util.*;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GraphGeneratorTest {

    private String testCsvFile = "./resources/graphGeneratorTest.csv";
    private Set<String> words;

    private GraphProcessor<String> graphProcessor = mock(GraphProcessor.class);
    private GraphGenerator graphGenerator = new GraphGenerator(graphProcessor);

    private static final String HIT = "hit";
    private static final String DOT = "dot";
    private static final String DOG = "dog";
    private static final String COG = "cog";
    private static final String HOT = "hot";
    private static final String LOG = "log";

    @Before
    public void setUp() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(testCsvFile).getFile());
        words = CsvFileReader.parse(file);
    }

    @Test
    public void generateGraph() {

        when(graphProcessor.linkVertices(eq(HIT), eq(DOT))).thenReturn(false);
        when(graphProcessor.linkVertices(eq(HIT), eq(DOG))).thenReturn(false);
        when(graphProcessor.linkVertices(eq(HIT), eq(COG))).thenReturn(false);
        when(graphProcessor.linkVertices(eq(HIT), eq(HOT))).thenReturn(true);
        when(graphProcessor.linkVertices(eq(HIT), eq(LOG))).thenReturn(false);

        when(graphProcessor.linkVertices(eq(DOT), eq(HIT))).thenReturn(false);
        when(graphProcessor.linkVertices(eq(DOT), eq(DOG))).thenReturn(false);
        when(graphProcessor.linkVertices(eq(DOT), eq(COG))).thenReturn(false);
        when(graphProcessor.linkVertices(eq(DOT), eq(HOT))).thenReturn(true);
        when(graphProcessor.linkVertices(eq(DOT), eq(LOG))).thenReturn(false);

        when(graphProcessor.linkVertices(eq(DOG), eq(HIT))).thenReturn(false);
        when(graphProcessor.linkVertices(eq(DOG), eq(DOT))).thenReturn(false);
        when(graphProcessor.linkVertices(eq(DOG), eq(COG))).thenReturn(true);
        when(graphProcessor.linkVertices(eq(DOG), eq(HOT))).thenReturn(false);
        when(graphProcessor.linkVertices(eq(DOG), eq(LOG))).thenReturn(true);

        when(graphProcessor.linkVertices(eq(COG), eq(HIT))).thenReturn(false);
        when(graphProcessor.linkVertices(eq(COG), eq(DOT))).thenReturn(false);
        when(graphProcessor.linkVertices(eq(COG), eq(DOG))).thenReturn(true);
        when(graphProcessor.linkVertices(eq(COG), eq(HOT))).thenReturn(false);
        when(graphProcessor.linkVertices(eq(COG), eq(LOG))).thenReturn(true);

        when(graphProcessor.linkVertices(eq(HOT), eq(HIT))).thenReturn(true);
        when(graphProcessor.linkVertices(eq(HOT), eq(DOT))).thenReturn(true);
        when(graphProcessor.linkVertices(eq(HOT), eq(DOG))).thenReturn(false);
        when(graphProcessor.linkVertices(eq(HOT), eq(COG))).thenReturn(false);
        when(graphProcessor.linkVertices(eq(HOT), eq(LOG))).thenReturn(true);

        when(graphProcessor.linkVertices(eq(LOG), eq(HIT))).thenReturn(false);
        when(graphProcessor.linkVertices(eq(LOG), eq(DOT))).thenReturn(false);
        when(graphProcessor.linkVertices(eq(LOG), eq(DOG))).thenReturn(true);
        when(graphProcessor.linkVertices(eq(LOG), eq(COG))).thenReturn(true);
        when(graphProcessor.linkVertices(eq(LOG), eq(HOT))).thenReturn(false);

        Map<String, Vertex> stringVertexMap = graphGenerator.create(words);

        assertEquals("no. outgoing for hit", 1, stringVertexMap.get(HIT).getOutGoingVertex().size());
        assertEquals("outgoing for hit", HOT, stringVertexMap.get(HIT).getOutGoingVertex().get(0).getValue());
        assertEquals("no. outgoing for dot", 1, stringVertexMap.get(DOT).getOutGoingVertex().size());
        assertEquals("outgoing for dot", HOT, stringVertexMap.get(DOT).getOutGoingVertex().get(0).getValue());
        assertEquals("no. outgoing for Dog", 2, stringVertexMap.get(DOG).getOutGoingVertex().size());
        assertEquals("outgoing for Dog", LOG, stringVertexMap.get(DOG).getOutGoingVertex().get(0).getValue());
        assertEquals("outgoing for Dog", COG, stringVertexMap.get(DOG).getOutGoingVertex().get(1).getValue());
        assertEquals("no. outgoing for cog", 2, stringVertexMap.get(COG).getOutGoingVertex().size());
        assertEquals("outgoing for cog", DOG, stringVertexMap.get(COG).getOutGoingVertex().get(0).getValue());
        assertEquals("outgoing for cog", LOG, stringVertexMap.get(COG).getOutGoingVertex().get(1).getValue());

    }

    @Test
    public void emptyListPassedIn() {
        Set<String> empty = new HashSet();
        Map<String, Vertex> stringVertexMap = graphGenerator.create(empty);
        assertTrue("Empty map", stringVertexMap.isEmpty());
    }

    @Test
    public void nullPassedIn() {
        Map<String, Vertex> stringVertexMap = graphGenerator.create(null);
        assertTrue("Empty map", stringVertexMap.isEmpty());
    }

}
