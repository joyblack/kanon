package com.joy.kanon.algo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joy.kanon.enums.KColor;
import com.joy.kanon.model.*;
import lombok.Data;
import lombok.ToString;
import org.apache.catalina.User;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class SuperK {
    /**
     * K值
     */
    private int K = 3;
    /**
     * 外接圆所需的3个顶点
     */
    private static final int TOP_NUMBER = 3;

    /**
     * 路网
     */
    @JsonIgnore
    private NetWork netWork;

    /**
     * 计算出的顶点结果
     */
    private List<Triple> triples;

    /**
     * 计算出的外接圆属性
     */
    private List<Circle> circle;

    /**
     * 用户 - 区块
     */
    private List<UserWithBlock> userWithBlocks;

    /**
     * 用户 - 匿名区
     */
    private List<UserWithAnon> userWithAnons;

    public SuperK(int k, NetWork netWork) {
        K = k;
        this.netWork = netWork;
    }

    public void run(){
        /**
         * 获取用户轨迹点
         */
        List<Vertex> user = netWork.getMovePoint();

        /**
         * 依次获取每个点所在的网格
         */
        userWithBlocks = new ArrayList<>();
        for (Vertex u : user) {
            for (Block block : netWork.getBlocks()) {
                if(Block.isInPolygon(u, block)){
                    userWithBlocks.add(new UserWithBlock(u, block));
                    break;
                }
            }
        }

        /**
         * 数据有问题
         */
        if(userWithBlocks.size() != user.size()){
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
            int step = 1;
            while (num < K) {
                if(addToAnons(cells[cellX - step][cellY - step],anon)){
                    break;
                }
                if(addToAnons(cells[cellX][cellY - step],anon)){
                    break;
                }
                if(addToAnons(cells[cellX + step][cellY - step],anon)){
                    break;
                }
                if(addToAnons(cells[cellX + step][cellY],anon)){
                    break;
                }
                if(addToAnons(cells[cellX + step][cellY + step],anon)){
                    break;
                }
                if(addToAnons(cells[cellX][cellY + step],anon)){
                    break;
                }
                if(addToAnons(cells[cellX - step][cellY + step],anon)){
                    break;
                }
                if(addToAnons(cells[cellX - step][cellY],anon)){
                    break;
                }
                step ++;
            }
            userWithAnons.add(anon);
        }
    }

    private boolean addToAnons(Cell cell, UserWithAnon anon){
        System.out.println(cell.getColor());
        if(cell.getColor().equals(KColor.THAN.getColor())){
            anon.getCells().add(cell);
            if(anon.getCells().size() == K){
                return true;
            }
        }
        return false;
    }
}
