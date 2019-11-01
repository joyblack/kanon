package com.joy.kanon.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class StatisticResult {
    /**
     * 预所有K点测出的总路段数之和
     */
    private int total;

    /**
     * 预所有K点测出的匹配路段数之和
     */
    private int correct;

    /**
     * 真实路段数量
     */
    private int trueNum;

    /**
     * 正确率
     */
    private double rate;

    /**
     * K值
     */
    private int k;

}
