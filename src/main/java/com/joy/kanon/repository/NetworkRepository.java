package com.joy.kanon.repository;

import com.joy.kanon.model.Location;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 构建网络
 */
@Repository
public class NetworkRepository {
    public static final List<Location> data;

    static {
        System.out.println("加载基础数据...");
        data = new ArrayList<>();
        System.out.println("加载完毕，共加载了" + data.size() + "条数据...");

    }


}
