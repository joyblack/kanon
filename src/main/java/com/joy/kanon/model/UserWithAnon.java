package com.joy.kanon.model;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户以及为其模拟的伪造区域数据
 */
@Data
@ToString
public class UserWithAnon {
    /**
     * 用户实际节点
     */
    private Vertex user;
    /**
     * 选取到的发布网格
     */
    private List<Cell> cells;

    /**
     * 匿名区圆心
     */
    private Vertex o;

    /**
     * 匿名区半径
     */
    private int r;


    public UserWithAnon() {
        cells = new ArrayList<>();
    }

    public UserWithAnon(Vertex user, List<Cell> cells) {
        this.user = user;
        this.cells = cells;
    }
}
