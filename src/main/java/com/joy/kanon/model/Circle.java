package com.joy.kanon.model;

import lombok.Data;
import lombok.ToString;

/**
 * 圆
 */
@Data
@ToString
public class Circle {
    private Vertex user;
    private Vertex o;
    private double r;

    public Circle(Vertex o, double r, Vertex user) {
        this.o = o;
        this.r = r;
        this.user = user;
    }
}
