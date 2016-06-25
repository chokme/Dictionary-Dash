package com.local.mez.dictionarydash.domain;

import java.util.ArrayList;
import java.util.List;

public class Vertex implements Comparable<Vertex>{

    private String value;
    private Long minDistanceFromSource;
    private List<Vertex> outGoingVertex;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getMinDistanceFromSource() {
        return minDistanceFromSource;
    }

    public void setMinDistanceFromSource(Long minDistanceFromSource) {
        this.minDistanceFromSource = minDistanceFromSource;
    }

    public List<Vertex> getOutGoingVertex() {
        return outGoingVertex;
    }

    public void setOutGoingVertex(List<Vertex> outGoingVertex) {
        this.outGoingVertex = outGoingVertex;
    }

    public void addOutGoingVertex(Vertex v) {
        if(this.getOutGoingVertex() == null) {
            List<Vertex> vertices = new ArrayList();
            vertices.add(v);
            this.setOutGoingVertex(vertices);
        }
        else {
            this.getOutGoingVertex().add(v);
        }

    }

    public int compareTo(Vertex o) {
        if(this.getMinDistanceFromSource() == null && o.getMinDistanceFromSource() == null ) {
            return 0;
        }
        else if (this.getMinDistanceFromSource() != null && o.getMinDistanceFromSource() == null) {
            return -1;
        }
        else if (this.getMinDistanceFromSource() == null) {
            return 1;
        }
        return this.getMinDistanceFromSource().compareTo(o.getMinDistanceFromSource());
    }
}
