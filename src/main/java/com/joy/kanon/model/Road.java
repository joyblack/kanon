package com.joy.kanon.model;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 构建道路
 */
@Data
@ToString
public class Road {
    /**
     * 节点坐标信息
     */
    Location node;

    /**
     * 节点邻居节点信息
     */
    List<Road> neighborList;

    private boolean visited;

    public Road(Location node) {
        this.node = node;
        neighborList = new ArrayList<>();
        visited = false;
    }

    @Override
    public boolean equals(Object other){
        if(other instanceof Road){
            Road b = (Road) other;
            return this.node.equals(b.node);
        }
        return false;
    }




}
