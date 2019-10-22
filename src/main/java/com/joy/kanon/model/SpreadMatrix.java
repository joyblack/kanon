package com.joy.kanon.model;

import com.joy.kanon.config.NetworkConfig;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 矩阵扩散
 */
@Data
@ToString
public class SpreadMatrix {
    /**
     * 边界点
     */
    private Point leftTop;
    private Point rightBottom;

    /**
     * 扩散中心点
     */
    private Point o;

    /**
     * 扩散半径
     */
    private int r;

    public SpreadMatrix(Point o) {
        this.o = o;
        this.r = 0;
        this.leftTop = new Point(0,0);
        this.rightBottom = new Point(NetworkConfig.WIDTH , NetworkConfig.WIDTH);
    }

    /**
     * 下一轮辐射边界点
     */
    public List<Point> next(){
        List<Point> result = new ArrayList<>();
        this.r ++;

        /**
         * 计算边长
         */
        int sideLength = this.r * 2 + 1;

        /**
         * 左上角点，依次向右(x++)和向下(y++)扩散
         * 左上角不到达sideLength,交由右下角节点到达
         */
        int leftTopX = o.getX() - r;
        int leftTopY = o.getY() - r;
        result.add(new Point(leftTopX, leftTopY));
        for (int i = 1; i < sideLength - 1; i++) {
            // down
            result.add(new Point(leftTopX + i, leftTopY));
            // right
            result.add(new Point(leftTopX, leftTopY + i));
        }

        /**
         * 右下角点
         */
        int rightBottomX = o.getX() + r;
        int rightBottomY = o.getY() + r;
        result.add(new Point(rightBottomX, rightBottomY));
        for (int i = 1; i < sideLength; i++) {
            // left
            result.add(new Point(rightBottomX - i, rightBottomY));
            // up
            result.add(new Point(rightBottomX, rightBottomY - i));
        }
        result = result.stream().filter(this::isValid).collect(Collectors.toList());
        return result;
    }


    /**
     * 若点位于指定方格中，则添加
     */
    private boolean isValid(Point p){
        if(p.getX() >= leftTop.getX() && p.getY() >= leftTop.getY()
            && p.getX() <= rightBottom.getX() && p.getY() <= rightBottom.getY()
        ){
            return true;
        }else{
            return false;
        }
    }



    public static void main(String[] args) {
        SpreadMatrix spreadMatrix = new SpreadMatrix(new Point(5, 5));
        for (int i = 0; i < 101; i++) {
            List<Point> next = spreadMatrix.next();
            System.out.println("总点数为: " + next.size());
        }
    }


}
