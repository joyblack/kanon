package com.joy.kanon.model;


import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 多边形网格
 */
@Data
@ToString
public class Block {
    /**
     * 顶点列表
     */
    private List<Vertex> vertexes;

    /**
     * 质心点
     */
    private Vertex o;

    public Block(List<Vertex> vertexes, Vertex o) {
        this.vertexes = vertexes;
        this.o = o;
    }

    /// 判断点是否在多边形内.
    /// ----------原理----------
    /// 注意到如果从P作水平向左的射线的话，如果P在多边形内部，那么这条射线与多边形的交点必为奇数，
    /// 如果P在多边形外部，则交点个数必为偶数(0也在内)。
    /// </summary>
    /// <param name="checkPoint">要判断的点</param>
    /// <param name="polygonPoints">多边形的顶点</param>
    /// <returns></returns>
    public static boolean isInPolygon(Vertex v, Block b)
    {
        boolean inside = false;
        int pointCount = b.getVertexes().size();
        List<Vertex> polygonPoints = b.getVertexes();
        Vertex p1, p2;
        for (int i = 0, j = pointCount - 1; i < pointCount; j = i, i++)//第一个点和最后一个点作为第一条线，之后是第一个点和第二个点作为第二条线，之后是第二个点与第三个点，第三个点与第四个点...
        {
            p1 = polygonPoints.get(i);
            p2 = polygonPoints.get(j);
            if (v.getY() < p2.getY())
            {//p2在射线之上
                if (p1.getY() <= v.getY())
                {//p1正好在射线中或者射线下方
                    if ((v.getY() - p1.getY()) * (p2.getX() - p1.getX()) > (v.getX() - p1.getX()) * (p2.getY() - p1.getY()))//斜率判断,在P1和P2之间且在P1P2右侧
                    {
                        //射线与多边形交点为奇数时则在多边形之内，若为偶数个交点时则在多边形之外。
                        //由于inside初始值为false，即交点数为零。所以当有第一个交点时，则必为奇数，则在内部，此时为inside=(!inside)
                        //所以当有第二个交点时，则必为偶数，则在外部，此时为inside=(!inside)
                        inside = (!inside);
                    }
                }
            }
            else if (v.getY() < p1.getY())
            {
                //p2正好在射线中或者在射线下方，p1在射线上
                if ((v.getY() - p1.getY()) * (p2.getX() - p1.getX()) < (v.getX() - p1.getX()) * (p2.getY() - p1.getY()))//斜率判断,在P1和P2之间且在P1P2右侧
                {
                    inside = (!inside);
                }
            }
        }
        return inside;
    }
}
