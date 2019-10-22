package com.joy.kanon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
