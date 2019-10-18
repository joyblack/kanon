package com.joy.kanon.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Tuple2<T> {
    private T v1;

    private T v2;

    public Tuple2(T v1, T v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

}
