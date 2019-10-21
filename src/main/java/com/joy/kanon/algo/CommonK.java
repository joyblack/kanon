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
public class CommonK {
    /**
     * 路网
     */
    @JsonIgnore
    private NetWork netWork;

    /**
     * 用户 - 区块
     */
    private List<UserWithBlock> userWithBlocks;

    /**
     * 用户 - 匿名区
     */
    private List<UserWithAnon> userWithAnons;

    public CommonK(NetWork netWork) {
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
         * 查找用户点周边K个最适合的区块点
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
            List<RoundFactor> factors = RoundFactor.generateFactor();
            while (num < NetworkConfig.K && factors.size() > 0) {
                List<RoundFactor> moved = new ArrayList<>();
                for (int i = 0; i <= factors.size(); i++) {
                    RoundFactor roundFactor = factors.get(i);
                    int x = cellX + roundFactor.getX() * step;
                    int y = cellY + roundFactor.getY() * step;
                    if(x < 0 || x == NetworkConfig.WIDTH || y < 0 || y == NetworkConfig.WIDTH){
                        moved.add(roundFactor);
                        continue;
                    }
                    addToAnons(cells[cellX - step][cellY - step],anon);

                }
                /**
                 * 删除环绕因子
                 */
                if(moved.size() > 0){
                    System.out.println("删除了" + moved.size() + "个环绕因子...");
                    factors.removeAll(moved);
                }
                step ++;
            }
            userWithAnons.add(anon);
        }
    }

    private boolean addToAnons(Cell cell, UserWithAnon anon){
        if(cell.getColor().equals(KColor.THAN.getColor())){
            anon.getCells().add(cell);
            if(anon.getCells().size() == NetworkConfig.K){
                return true;
            }
        }
        return false;
    }
}
