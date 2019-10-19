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
    private Vertex user;
    private List<Cell> cells;

    public UserWithAnon() {
        cells = new ArrayList<>();
    }

    public UserWithAnon(Vertex user, List<Cell> cells) {
        this.user = user;
        this.cells = cells;
    }
}
