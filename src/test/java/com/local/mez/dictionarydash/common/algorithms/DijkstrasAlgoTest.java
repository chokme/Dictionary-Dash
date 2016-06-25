package com.local.mez.dictionarydash.common.algorithms;

import com.local.mez.dictionarydash.domain.Vertex;
import org.junit.Before;
import org.junit.Test;

import java.util.PriorityQueue;
import java.util.Stack;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DijkstrasAlgoTest {

    private DijkstrasAlgo dijkstrasAlgo = new DijkstrasAlgo();
    private Vertex start;
    private Vertex end;
    private PriorityQueue<Vertex> priorityQueue;

    @Before
    public void setUp() {
        start = new Vertex();
        start.setValue("start");
        end = new Vertex();
        end.setValue("end");
        priorityQueue = new PriorityQueue<Vertex>();
    }

    @Test
    public void startVertexIsNull() {
        Stack<String> result = dijkstrasAlgo.execute(null, end, priorityQueue);
        assertTrue("empty stack", result.isEmpty());
    }

    @Test
    public void endVertexIsNull() {
        Stack<String> result = dijkstrasAlgo.execute(start, null, priorityQueue);
        assertTrue("empty stack", result.isEmpty());
    }

    @Test
    public void priorityQueueIsNull() {

        Stack<String> result = dijkstrasAlgo.execute(start, end, null);
        assertTrue("empty stack", result.isEmpty());
    }

    @Test
    public void startNotInPriorityQueue() {

        Stack<String> result = dijkstrasAlgo.execute(start, end, priorityQueue);
        assertTrue("empty stack", result.isEmpty());
    }

    @Test
    public void endNotInPriorityQueue() {
        priorityQueue.add(start);

        Stack<String> result = dijkstrasAlgo.execute(start, end, priorityQueue);
        assertTrue("empty stack", result.isEmpty());
    }

    @Test
    public void startAndEndVertexAreTheSame() {
        priorityQueue.add(start);

        Stack<String> result = dijkstrasAlgo.execute(start, start, priorityQueue);
        assertTrue("return stack of 1", result.size() == 1);
        assertTrue("value is start", result.pop().equals("start"));
    }

    @Test
    public void startAndEndHaveNoLink() {
        priorityQueue.add(start);
        priorityQueue.add(end);

        Vertex v1 = new Vertex();
        v1.setValue("v1");

        Vertex v2 = new Vertex();
        v2.setValue("v2");

        start.addOutGoingVertex(v1);
        v1.addOutGoingVertex(v2);
        v1.addOutGoingVertex(start);
        v2.addOutGoingVertex(v1);

        priorityQueue.add(v1);
        priorityQueue.add(v2);

        Stack<String> result = dijkstrasAlgo.execute(start, end, priorityQueue);
        assertTrue("empty stack", result.isEmpty());
    }

    @Test
    public void startAndEndHaveLink() {
        priorityQueue.add(start);
        priorityQueue.add(end);

        Vertex v1 = new Vertex();
        v1.setValue("v1");

        Vertex v2 = new Vertex();
        v2.setValue("v2");

        start.addOutGoingVertex(v1);
        v1.addOutGoingVertex(v2);
        v1.addOutGoingVertex(start);
        v2.addOutGoingVertex(v1);
        v2.addOutGoingVertex(end);
        end.addOutGoingVertex(v2);

        priorityQueue.add(v1);
        priorityQueue.add(v2);

        Stack<String> result = dijkstrasAlgo.execute(start, end, priorityQueue);

        Stack<String> expectedResult = new Stack();
        expectedResult.push(start.getValue());
        expectedResult.push(v1.getValue());
        expectedResult.push(v2.getValue());
        expectedResult.push(end.getValue());

        assertTrue("stack is not empty", !result.isEmpty());
        assertEquals("output", expectedResult.toString(), result.toString());
    }


}
