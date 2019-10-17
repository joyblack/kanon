package com.joy.kanon.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 用户位置
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    /**
     * 行为：新或者旧或者消失
     */
    private StatusEum status;

    /**
     * 对象ID
     */
    private Long id;

    /**
     * 回报编码
     */
    private Long repNum;

    /**
     * 对象类型
     */
    private Long objClass;

    /**
     * 所处当前位置的时间(从0开始计算)
     */
    private Long time;

    /**
     * 精度
     */
    private Double x;

    /**
     * 维度
     */
    private Double y;

    /**
     * 运行速度
     */
    private Double speed;

    /**
     * 下一个运动点的预测经纬度
     */
    private Double nextX;
    private Double nextY;
}
