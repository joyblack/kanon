package com.joy.kanon.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * 用户位置
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    /**
     * 精度
     */
    private Double x;

    /**
     * 维度
     */
    private Double y;

    /**
     * 所处当前位置的时间
     */
    private Date t;
}
