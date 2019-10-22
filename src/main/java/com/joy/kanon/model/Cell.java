package com.joy.kanon.model;

import lombok.Data;
import lombok.ToString;

/**
 *
 */
@Data
@ToString
public class Cell {
    // 网格横纵坐标
    private int x;
    private int y;
    // 网格具备点数
    private int number;
    // 网格颜色
    private String color;
    public Cell(int x, int y, int number, String color) {
        this.x = x;
        this.y = y;
        this.number = number;
        this.color = color;
    }
}
