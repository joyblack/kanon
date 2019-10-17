package com.joy.kanon.repository;

import com.joy.kanon.model.Location;
import com.joy.kanon.model.StatusEum;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 运行时加载数据
 */
@Repository
public class DataRepository {
    public static final List<Location> data;

    static {
        System.out.println("加载基础数据...");
        data = new ArrayList<>();
        try {
            List<List<String>> lines = Files.readAllLines(Paths.get("tiger.dat")).stream()
                    .map(line -> Arrays.asList(line.split("\t")))
                    .collect(Collectors.toList());
            for (List<String> line : lines) {
                Location location = new Location();
                location.setStatus(StatusEum.get(line.get(0)));
                location.setId(Long.valueOf(line.get(1)));
                location.setRepNum(Long.valueOf(line.get(2)));
                location.setObjClass(Long.valueOf(line.get(3)));
                location.setTime(Long.valueOf(line.get(4)));
                location.setX(Double.valueOf(line.get(5)));
                location.setY(Double.valueOf(line.get(6)));
                location.setSpeed(Double.valueOf(line.get(7)));
                location.setNextX(Double.valueOf(line.get(8)));
                location.setNextY(Double.valueOf(line.get(9)));
                data.add(location);
            }
            System.out.println("加载完毕，共加载了" + data.size() + "条数据...");
        } catch (IOException e) {
            throw new RuntimeException("无法加载数据文件");
        }
    }


}
