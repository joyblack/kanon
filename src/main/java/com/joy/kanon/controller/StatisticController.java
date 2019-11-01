package com.joy.kanon.controller;

import com.joy.kanon.algo.Statistic;
import com.joy.kanon.controller.req.KArrReq;
import com.joy.kanon.model.StatisticResult;
import com.joy.kanon.repository.NetworkRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("statistic")
public class StatisticController {
    /**
     * 统计首页
     */
    @RequestMapping("index")
    public String index(){
        return "statistic/index";
    }

    @RequestMapping("getSuperK/{k}")
    @ResponseBody
    public StatisticResult getSuperK(@PathVariable int k){
        Statistic statistic = new Statistic(NetworkRepository.netWork);
        return statistic.getSuperK(k);
    }

    @RequestMapping("getCommonK/{k}")
    @ResponseBody
    public StatisticResult getCommonK(@PathVariable int k){
        Statistic statistic = new Statistic(NetworkRepository.netWork);
        return statistic.getCommonK(k);
    }

    @RequestMapping("getCommonKS")
    @ResponseBody
    public List<StatisticResult> getCommonKS(@RequestBody KArrReq req){
        System.out.println(req);
        List<StatisticResult> result = new ArrayList<>();
        Statistic statistic = new Statistic(NetworkRepository.netWork);
        for (int k : req.getKs()) {
            result.add(statistic.getCommonK(k));
        }
        return result;
    }

    @RequestMapping("getSuperKS")
    @ResponseBody
    public List<StatisticResult> getSuperKS(@RequestBody KArrReq req){
        System.out.println(req);
        List<StatisticResult> result = new ArrayList<>();
        Statistic statistic = new Statistic(NetworkRepository.netWork);
        for (int k : req.getKs()) {
            result.add(statistic.getSuperK(k));
        }
        return result;
    }


}
