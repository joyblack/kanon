package com.joy.kanon.util;

import com.joy.kanon.model.Edge;
import com.joy.kanon.model.Vertex;

public class ComputeUtil {
    public static final double SQUARE_NUMBER = 2.0;

    public static final double MAX_DISTANCE = Double.MAX_VALUE;

    /**
     * 计算两点间距离
     */
    public static double getInstance(Vertex a, Vertex b){
        int x = a.getX() - b.getX();
        int y = a.getY() - b.getY();
        return Math.sqrt(x*x + y*y);
    }


    /**
     * 计算点到线段之间的距离
     */
    public static double getInstance(Edge e, Vertex v) {
        double ab = e.getLength();
        double ac = getInstance(e.getV1(), v);
        double bc = getInstance(e.getV2(), v);
        if (ab == (ac + bc)) {
            return 0;
        }
        /**
         *  组成直角三角形或钝角三角形，投影在point1延长线上，
         */
        if (ac * ac >= ab * ab + bc * bc) {
            return bc;
        }
        /**
         * 组成直角三角形或钝角三角形，投影在point2延长线上
         */
        if (bc * bc >= ab * ab + ac * ac) {
            return ac;
        }
        // 半周长
        double p = (ab + bc + ac) / 2;
        double s = Math.sqrt(p * (p - ab) * (p - bc) * (p - ac));// 海伦公式求面积
        return (int)(2 * s / ab);// 返回点到线的距离（利用三角形面积公式求高）
    }

}
