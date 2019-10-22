package com.joy.kanon.repository;

import com.joy.kanon.config.NetworkConfig;
import com.joy.kanon.model.NetWork;

public class NetworkRepository {
    public static NetWork netWork;
    static {
        netWork = new NetWork(NetworkConfig.getDefaultConfig());
    }
}
