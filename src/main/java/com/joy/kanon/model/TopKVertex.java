package com.joy.kanon.model;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class TopKVertex {
    private Vertex vertex;
    private double distance;

    public TopKVertex(Vertex vertex, double distance) {
        this.vertex = vertex;
        this.distance = distance;
    }
}
