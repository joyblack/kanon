package com.joy.kanon.algo;

import com.joy.kanon.model.Edge;

import java.util.List;

/**
 *
 */
public class Statistic {
    /**
     * 计算识别路段的正确率
     * 总值为识别得到的路段数与正确的路段数取最大值，分子为识别正确的路段数
     */
    private static double run(List<Edge> correctE, List<Edge> computeE){
        if(computeE == null || correctE == null || computeE.size() == 0 || correctE.size() == 0){
            return 0;
        }else{
            int correctNum = 0;
            int total = Math.max(computeE.size(),correctE.size());
            for (int i = 0; i < correctE.size(); i++) {
                if(i < computeE.size() && computeE.get(i).equals(correctE.get(i))){
                    correctNum ++;
                }
            }
            System.out.println("正确的总路段数为: " + correctE.size());
            System.out.println("识别出的总路段数为: " + computeE.size());
            System.out.println("正确的路段数为：" + correctNum);
            return correctNum/(total * 1.0);
        }
    }





}
