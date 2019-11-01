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
        System.out.println("--------------- 攻击算法 -------------------------");
        /**
         * 打印推测出的用户位置点
         */
        System.out.println("参考顶点信息为：" + referVertexes);
        /**
         * 依次遍历路网中的所有路段，将每个点作为参考点查询
         */
        List<Edge> result = new ArrayList<>();
        for (Vertex vertex : referVertexes) {
            double min = ComputeUtil.MAX_DISTANCE;
            Edge path = null;
            /**
             * 提取距离位置点最近的边
             */
            for (Edge edge : netWork.getEdges()) {
                double distance = ComputeUtil.getInstance(edge, vertex);
                if(distance < min){
                    min = distance;
                    path = edge;
                }
            }
            /**
             * 提取出的最近的边，判断当前检索边集中是否包含，若不包含，加入探测边集。
             */
            if(!result.contains(path)){
                result.add(path);
            }
        }
        /**
         * 打印攻击结果
         */
        System.out.println("计算出的边信息为：" + Edge.show(result));
        return result;
    }
}
