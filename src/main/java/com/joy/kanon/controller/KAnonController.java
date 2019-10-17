package com.joy.kanon.controller;

import com.joy.kanon.model.Location;
import com.joy.kanon.repository.DataRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("kanon")
@Controller
public class KAnonController {
    @RequestMapping("index")
    public String index(){
        return "kanon/index";
    }

    @RequestMapping("getData")
    @ResponseBody
    public List<Location> getData(){
        return DataRepository.data;
    }

    @RequestMapping("getFormatData")
    @ResponseBody
    public List<Location> getFormatData(){
        for (Location datum : DataRepository.data) {
            datum.setNextX(Math.floor(datum.getNextX()));
            datum.setNextY(Math.floor(datum.getNextY()));
            datum.setX(Math.floor(datum.getX()));
            datum.setY(Math.floor(datum.getY()));
            datum.setSpeed(Math.floor(datum.getSpeed()));
        }
        return DataRepository.data;
    }
}
