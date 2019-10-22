package com.joy.kanon.algo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joy.kanon.config.NetworkConfig;
import com.joy.kanon.enums.KColor;
import com.joy.kanon.model.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class SuperK {
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

    public SuperK(NetWork netWork, int k) {
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
         * 根据质心点，查找构造区，查找K个最适合的区块点
         */
        userWithAnons = new ArrayList<>();
        for (Vertex u : user) {
            UserWithAnon anon = new UserWithAnon();
            anon.setUser(u);
            // 在该用户所在区块进行查找
            UserWithBlock userWithBlock = userWithBlocks.stream().filter(b -> b.getUser().getName().equals(u.getName())).findFirst().orElseGet(null);
            if(userWithBlock == null){
                throw new RuntimeException("网络路况数据有误，无法定位用户所在区块，请检查...");
            }
            int cellX = userWithBlock.getBlock().getO().getX() / 10;
            int cellY = userWithBlock.getBlock().getO().getY() / 10;

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
            anon.setO(userWithBlock.getBlock().getO());
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
