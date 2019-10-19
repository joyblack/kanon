package com.joy.kanon.model;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 用户真实点与其对应的三个外接圆顶点
 * 小心共线问题
 */
@Data
@ToString
public class Triple {
    private Vertex userVertex;
    private Vertex tripleA;
    private Vertex tripleB;
    private Vertex tripleC;

    public Triple(Vertex userVertex, Vertex tripleA, Vertex tripleB, Vertex tripleC) {
        this.userVertex = userVertex;
        this.tripleA = tripleA;
        this.tripleB = tripleB;
        this.tripleC = tripleC;
    }
}
