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
public class Graphic {
    /**
     * 节点坐标信息
     */
    Vertex vertex;

    /**
     * 节点邻居节点信息
     */
    List<Graphic> neighborList;

    private boolean visited;

    public Graphic(Vertex vertex) {
        this.vertex = vertex;
        neighborList = new ArrayList<>();
        visited = false;
    }

    @Override
    public boolean equals(Object other){
        if(other instanceof Graphic){
            Graphic b = (Graphic) other;
            return this.vertex.equals(b.vertex);
        }
        return false;
    }




}
