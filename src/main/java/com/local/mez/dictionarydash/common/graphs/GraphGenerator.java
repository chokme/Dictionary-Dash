package com.local.mez.dictionarydash.common.graphs;

import com.local.mez.dictionarydash.domain.Vertex;

import java.util.*;

public class GraphGenerator {

    private GraphProcessor<String> graphProcessor;

    public GraphGenerator(GraphProcessor<String> graphProcessor) {
        this.graphProcessor = graphProcessor;
    }

    public Map<String, Vertex>  create(Set<String> strings) {

        Map<String, Vertex> verticesMap = new HashMap();
        if(strings == null || strings.isEmpty()) {
            return verticesMap;
        }

        Stack<String> stringStack = new Stack();
        stringStack.addAll(strings);

        while(!stringStack.empty()) {
            String current = stringStack.pop();
            Vertex currentVertex = verticesMap.get(current);

            if(currentVertex == null) {
                currentVertex = new Vertex();
                currentVertex.setValue(current);
                verticesMap.put(current, currentVertex);
            }

            for(String s : stringStack) {
                if(graphProcessor.linkVertices(current, s)) {
                    Vertex matchingVertex = verticesMap.get(s);

                    if(matchingVertex == null) {
                        matchingVertex = new Vertex();
                        matchingVertex.setValue(s);
                        verticesMap.put(s, matchingVertex);
                    }

                    currentVertex.addOutGoingVertex(matchingVertex);
                    matchingVertex.addOutGoingVertex(currentVertex);
                }
            }
        }

        return verticesMap;

    }
}
