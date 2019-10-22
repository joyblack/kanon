package com.joy.kanon.controller;

import com.joy.kanon.algo.Statistic;
import com.joy.kanon.model.StatisticResult;
import com.joy.kanon.model.Vertex;
import com.joy.kanon.repository.NetworkRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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


}
