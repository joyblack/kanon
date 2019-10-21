package com.joy.kanon.algo;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 环绕因子
 */
@Data
@ToString
public class RoundFactor {
    private int x;
    private int y;

    public RoundFactor(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 获取环绕因子，设置为对象类型，主要是为了删减需要。
     */
    public static List<RoundFactor> generateFactor(){
        return Arrays.asList(
                new RoundFactor(-1, -1),
                new RoundFactor(0, -1),
                new RoundFactor(1, -1),
                new RoundFactor(1, 0),
                new RoundFactor(1, 1),
                new RoundFactor(0, 1),
                new RoundFactor(-1, 1),
                new RoundFactor(-1, 0)
        );
    }


}
