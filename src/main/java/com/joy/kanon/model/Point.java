package com.joy.kanon.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
