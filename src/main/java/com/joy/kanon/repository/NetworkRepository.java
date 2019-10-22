package com.joy.kanon.repository;

import com.joy.kanon.model.NetWork;

import static com.joy.kanon.config.NetworkConfig.getDefaultConfig;

public class NetworkRepository {
    public static NetWork netWork;
    static {
        netWork = new NetWork(getDefaultConfig());
    }
}
