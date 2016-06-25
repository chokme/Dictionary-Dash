package com.local.mez.dictionarydash.common.algorithms;

import com.local.mez.dictionarydash.domain.Vertex;

import java.util.PriorityQueue;
import java.util.Stack;

public interface ShortestPath {

    Stack<String> execute(Vertex start, Vertex end, PriorityQueue<Vertex> priorityQueue);
}
