package com.joy.kanon.config;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class NetworkConfig {
    /**
     * 网图长宽
     */
    public static final int WIDTH = 100;

    /**
     * 网格大小
     */
    public static  final int CELL_SIZE = 10;

    /**
     * 选取网格阈值
     */
    public int threshold;

    /**
     * 生成随机点个数
     */
    public int randomNum;

    public NetworkConfig(int threshold, int randomNum) {
        this.threshold = threshold;
        this.randomNum = randomNum;
    }

    public static NetworkConfig getDefaultConfig(){
        return new NetworkConfig(8, 40000);
    }

}
