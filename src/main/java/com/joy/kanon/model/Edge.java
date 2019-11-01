package com.joy.kanon.model;

import com.joy.kanon.util.ComputeUtil;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * 显示简单的边name信息
     */
    public static String show(List<Edge> edgeList){
        return edgeList.stream().map(Edge::getName).collect(Collectors.joining("->"));
    }
}
