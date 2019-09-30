package com.joy.kanon.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 位置隐私需求QC
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LocationQuery {
    /**
     * 请求ID
     */
    private Long id;

    /**
     * 位置信息
     */
    private Location location;

    /**
     * 用户端形成的匿名区最少包含用户真实位置和K-1个其他用户位置
     */
    private Integer K;




}
