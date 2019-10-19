package com.joy.kanon.model;

import com.joy.kanon.util.ComputeUtil;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Edge {
    private String name;
    private Vertex v1;
    private Vertex v2;
    private double length;

    public Edge() {
        super();
    }

    public Edge(String name, Vertex v1, Vertex v2) {
        this();
        this.name = name;
        this.v1 = v1;
        this.v2 = v2;
        length = ComputeUtil.getInstance(v1,v2);
    }
}
