package com.local.mez.dictionarydash.common.algorithms;

import com.local.mez.dictionarydash.domain.Vertex;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

public class DijkstrasAlgo implements ShortestPath {

    private Map<Vertex, Long> minDistanceFromSource = new HashMap();
    private Map<String, String> traveledFrom = new HashMap();
    private final Long defaultDistanceBetweenVerticies = new Long("1");


    public Stack<String> execute(Vertex start, Vertex end, PriorityQueue<Vertex> priorityQueue) {

        Stack<String> finalResult = new Stack();
        if(start == null || end == null || priorityQueue == null || !priorityQueue.contains(start) || !priorityQueue.contains(end)) {
            System.out.println("Inputs to algorithm were invalid");
            return finalResult;
        }
        if(start.getValue().equals(end.getValue())) {
            finalResult.push(start.getValue());
            return finalResult;
        }

        Vertex current = start;
        minDistanceFromSource.put(current, 0l);
        traveledFrom.put(current.getValue(), null);
        priorityQueue.remove(current);

        while(!priorityQueue.isEmpty()) {
            for(Vertex visiting : current.getOutGoingVertex()) {
                Long visitingMinDistance = minDistanceFromSource.get(visiting);
                Long newMinDistance = minDistanceFromSource.get(current) + defaultDistanceBetweenVerticies;
                if(visitingMinDistance == null || newMinDistance < visitingMinDistance) {
                    visiting.setMinDistanceFromSource(newMinDistance);
                    minDistanceFromSource.put(visiting, newMinDistance);
                    traveledFrom.put(visiting.getValue(), current.getValue());
                    priorityQueue.remove(visiting);
                    priorityQueue.offer(visiting);
                }
            }
            current = priorityQueue.poll();
            if(current.getValue().equals(end.getValue()) || current.getMinDistanceFromSource() == null) {
                break;
            }
        }

        finalResult = getFinalPath(traveledFrom, start.getValue(), end.getValue());
        return finalResult;
    }

    private Stack<String> getFinalPath(Map<String, String> traveledFrom, String start, String end) {
        Stack<String> path = new Stack();
        path.push(end);
        String child = end;
        while(!child.equals(start)) {
            String parent = traveledFrom.get(child);
            if(parent != null) {
                path.push(parent);
                child = parent;
            }
            else {
                return new Stack();
            }
        }

        Stack finalPath = new Stack();
        while(!path.empty()) {
            finalPath.push(path.pop());
        }

        return finalPath;

    }
}
