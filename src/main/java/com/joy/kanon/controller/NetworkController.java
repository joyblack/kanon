package com.joy.kanon.controller;

import com.joy.kanon.model.Location;
import com.joy.kanon.model.NetWork;
import com.joy.kanon.repository.NetworkRepository;
import org.springframework.stereotype.Controller;
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
    public List<Location> getBFS(){
        return NetworkRepository.netWork.getBfs();
    }

    @RequestMapping("getDFS")
    @ResponseBody
    public List<Location> getDFS(){
        return NetworkRepository.netWork.getDfs();
    }

}
