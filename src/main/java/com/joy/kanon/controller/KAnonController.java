package com.joy.kanon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("kanon")
@Controller
public class KAnonController {
    @RequestMapping("index")
    public String index(){
        return "kanon/index";
    }

}
