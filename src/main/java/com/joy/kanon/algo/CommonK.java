package com.joy.kanon.algo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joy.kanon.enums.KColor;
import com.joy.kanon.model.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 传统K匿名算法
 */
@Data
@ToString
public class CommonK {
    /**
     * K值
     */
    private int K;

    /**
     * 路网
     */
    @JsonIgnore
    private NetWork netWork;

    /**
     * 用户 - 匿名区
     */
    private List<UserWithAnon> userWithAnons;

    public CommonK(NetWork netWork, int k) {
        this.netWork = netWork;
        this.K = k;
    }

    public void run(){
        /**
         * 获取用户轨迹点
         */
        List<Vertex> user = netWork.getMovePoint();

        /**
         * 数据有问题
         */
        List<UserWithBlock> userWithBlocks = netWork.getUserWithBlocks();
        if(netWork.getUserWithBlocks().size() != user.size()){
            throw new RuntimeException("网络路况数据有误，请检查...");
        }

        /**
         * 根据用户点进行扩散，查找K个最适合的点（相当于自己就是圆心）
         */
        userWithAnons = new ArrayList<>();
        for (Vertex u : user) {
            UserWithAnon anon = new UserWithAnon();
            anon.setUser(u);
            int cellX = u.getX() / 10;
            int cellY = u.getY() / 10;

            /**
             * 选择合适的K个
             */
            Cell[][] cells = netWork.getCells();
            int num = 0;
            SpreadMatrix spreadMatrix = new SpreadMatrix(new Point(cellX, cellY));
            while (num < K) {
                List<Point> next = spreadMatrix.next();
                if(next.size() == 0){
                    break;
                }else{
                    for (Point point : next) {
                        if(addToAnons(cells[point.getX()][point.getY()],anon)){
                            num ++;
                            if(num == K){
                                break;
                            }
                        }
                    }
                }
            }
            /**
             * 匿名区圆心、半径
             * 半径应该为当前传播矩阵的半径 + 2 （+2是为了扩大一圈）
             */
            anon.setR(spreadMatrix.getR() + 2);
            userWithAnons.add(anon);
        }
    }

    private boolean addToAnons(Cell cell, UserWithAnon anon){
        if(cell.getColor().equals(KColor.THAN.getColor())){
            anon.getCells().add(cell);
            return true;
        }
        return false;
    }
}
