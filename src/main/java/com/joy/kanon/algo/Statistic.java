package com.joy.kanon.algo;

import com.joy.kanon.config.NetworkConfig;
import com.joy.kanon.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public class Statistic {

    private NetWork netWork;

    public Statistic(NetWork network) {
        this.netWork = network;
    }

    public int getMin(int ... arr){
        int min = Integer.MAX_VALUE;
        for (int i : arr) {
            if(i < min){
                min = i;
            }
        }
        return min;
    }

    public StatisticResult getSuperK(int k){
        SuperK superK = new SuperK(netWork, k);
        superK.run();

        /**
         * 分别选取每个点的一个网格作为参考点来解读,当然循环点必须是cell中最小的K点数量（因为可能某些区域无法获取满K个参考cell）
         * 即所谓的木桶效应。
         * 获取出参考点之后，针对K匿名算法中的每一个点，通过攻击算法（最近路段）选取出推测出的路段。
         */
        List<UserWithAnon> userWithAnons = superK.getUserWithAnons();
        /**
         * 推测出的边
         */
        List<List<Edge>> referEdges = new ArrayList<>();
        int minK = Integer.MAX_VALUE;
        for (UserWithAnon anon : userWithAnons) {
            int size = anon.getCells().size();
            if(size < minK){
                minK = size;
            }
        }
        /**
         * 分别选取每一个cells中的一个参考点，为了组成一个参考点路径，
         * 需要选取每个cell的参考点组成一个猜测路径
         *
         * 显然，需要选取K轮
         * 通过选取出的点实施攻击算法，获取计算路径
         * 需要注意，cell中存储的是cell参考规格坐标，因此，实际坐标需要扩大cellSize倍
         */
        for (int i = 0; i < minK; i++) {
            List<Vertex> referV = new ArrayList<>();
            for (UserWithAnon anon : userWithAnons) {
                referV.add(new Vertex("referEdge", 0,
                        anon.getCells().get(i).getX() * NetworkConfig.CELL_SIZE,
                        anon.getCells().get(i).getY() * NetworkConfig.CELL_SIZE));
            }
            referEdges.add(Attack.attack(netWork, referV));
        }

        /**
         * 计算正确率，正确率是以K个点分别作为参考点计算之后得到的结果，
         * 相当于K（min）轮检测结果的总统计。
         */
        StatisticResult statisticResult = getStatisticResult(netWork.getMovePath(), referEdges);
        statisticResult.setK(k);
        return statisticResult;
    }

    public StatisticResult getCommonK(int k){
        CommonK commonK = new CommonK(netWork, k);
        commonK.run();

        /**
         * 分别选取每个点的一个网格作为参考点来解读,当然循环点必须是cell中最小的K点数量（因为可能某些区域无法获取满K个参考cell）
         * 即所谓的木桶效应。
         * 获取出参考点之后，针对K匿名算法中的每一个点，通过攻击算法（最近路段）选取出推测出的路段。
         */
        List<UserWithAnon> userWithAnons = commonK.getUserWithAnons();
        /**
         * 推测出的边
         */
        List<List<Edge>> referEdges = new ArrayList<>();
        int minK = Integer.MAX_VALUE;
        for (UserWithAnon anon : userWithAnons) {
            int size = anon.getCells().size();
            if(size < minK){
                minK = size;
            }
        }
        /**
         * 分别选取每一个cells中的一个参考点，为了组成一个参考点路径，
         * 需要选取每个cell的参考点组成一个猜测路径
         *
         * 显然，需要选取K轮
         * 通过选取出的点实施攻击算法，获取计算路径
         * 需要注意，cell中存储的是cell参考规格坐标，因此，实际坐标需要扩大cellSize倍
         */
        for (int i = 0; i < minK; i++) {
            List<Vertex> referV = new ArrayList<>();
            for (UserWithAnon anon : userWithAnons) {
                referV.add(new Vertex("referEdge", 0,
                        anon.getCells().get(i).getX() * NetworkConfig.CELL_SIZE,
                        anon.getCells().get(i).getY() * NetworkConfig.CELL_SIZE));
            }
            referEdges.add(Attack.attack(netWork, referV));
        }

        /**
         * 计算正确率，正确率是以K个点分别作为参考点计算之后得到的结果，
         * 相当于K（min）轮检测结果的总统计。
         */
        StatisticResult statisticResult = getStatisticResult(netWork.getMovePath(), referEdges);
        statisticResult.setK(k);
        return statisticResult;
    }

    /**
     * 计算识别路段的正确率
     * 总值为识别得到的路段数与正确的路段数取最大值，分子为识别正确的路段数
     * computeE 所有K个点推导出的结果进行计算正确率
     */
    private StatisticResult getStatisticResult(List<Edge> correctE, List<List<Edge>> computeEList){
        System.out.println("#############################################");
        System.out.println("正确的路段信息为：" + Edge.show(correctE));
        System.out.println("计算出的边信息为：" + Edge.show(correctE));
        StatisticResult result = new StatisticResult();
        result.setTrueNum(netWork.getMovePath().size());
        if(computeEList == null  || computeEList.size() == 0 || correctE.size() == 0){
            return result;
        }else{
            int correct = 0;
            int total = 0;
            for (List<Edge> computeE : computeEList) {
                System.out.println("***************************");
                System.out.println("共计算出了" + computeE.size() + "条边");
                System.out.println("计算出的边信息为：" + Edge.show(computeE));
                int correctChild = 0;
                int totalChild =  Math.max(computeE.size(),correctE.size());
                for (int i = 0; i < correctE.size(); i++) {
                    if(i < computeE.size() && computeE.get(i).equals(correctE.get(i))){
                        correctChild ++;
                    }
                }
                System.out.println("当前轮次的识别的正确路段数为：" + correctChild);
                System.out.println("当前轮次的识别的总路段数为：" + totalChild);
                correct += correctChild;
                total += totalChild;
            }
            System.out.println("总识别出的路段数为: " + total);
            System.out.println("正确的路段数为：" + correct);
            result.setTotal(total);
            result.setCorrect(correct);
            result.setRate(correct/(total * 1.0));
            return result;
        }

    }





}
