package com.joy.kanon.controller;

import com.joy.kanon.algo.SuperK;
import com.joy.kanon.config.NetworkConfig;
import com.joy.kanon.model.Vertex;
import com.joy.kanon.model.NetWork;
import com.joy.kanon.repository.NetworkRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("network")
@Controller
public class NetworkController {


    @RequestMapping("index")
    public String index(){
        return "network/index";
    }

    @RequestMapping("loadData")
    @ResponseBody
    public NetWork loadData(){
        return NetworkRepository.netWork;
    }

    @RequestMapping("getBFS")
    @ResponseBody
    public List<Vertex> getBFS(){
        return NetworkRepository.netWork.getBfs();
    }

    @RequestMapping("getDFS")
    @ResponseBody
    public List<Vertex> getDFS(){
        return NetworkRepository.netWork.getDfs();
    }

    /**
     * 修改K值、阈值，返回superK结果
     */
    @RequestMapping("superK/{k}/{s}")
    @ResponseBody
    public NetWork getDFS(@PathVariable int k, @PathVariable int s){
        NetworkRepository.netWork.changeK(k, s);
        return NetworkRepository.netWork;
    }



}
