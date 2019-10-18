package com.joy.kanon.model;

import lombok.Data;
import lombok.ToString;

/**
 * 用户位置
 */
@Data
@ToString
public class Location {
    /**
     * 对象名称
     */
    private String name;

    /**
     * 所处当前位置的时间(从0开始计算)
     */
    private int time;

    /**
     * 坐标X
     */
    private int x;

    /**
     * 坐标Y
     */
    private int y;

    public Location(String name, int time, int x, int y) {
        this.name = name;
        this.time = time;
        this.x = x;
        this.y = y;
    }

    /**
     * 判断个点是否相等
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other){
        if(other instanceof Location){
            Location b = (Location) other;
            return this.name == b.name || this.x == b.x && this.y == b.y;
        }
        return false;
    }


}
