package com.joy.kanon.algo;

import com.joy.kanon.model.Edge;
import com.joy.kanon.model.NetWork;
import com.joy.kanon.model.Vertex;
import com.joy.kanon.util.ComputeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 攻击算法，根据参考点推导具体的路段信息
 */
public class Attack {
    /**
     * 距离参考点最近的边作为选取结果
     */
    public static List<Edge> attack(NetWork netWork, List<Vertex> referVertexes){
        List<Edge> result = new ArrayList<>();
        for (Vertex vertex : referVertexes) {
            double min = ComputeUtil.MAX_DISTANCE;
            Edge path = null;
            for (Edge edge : netWork.getEdges()) {
                double distance = ComputeUtil.getInstance(edge, vertex);
                if(distance < min){
                    min = distance;
                    path = edge;
                }
            }
            if(!result.contains(path)){
                result.add(path);
            }
        }
        return result;
    }
}
