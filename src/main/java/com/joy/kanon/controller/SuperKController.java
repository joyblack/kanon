package com.joy.kanon.controller;

import com.joy.kanon.algo.SuperK;
import com.joy.kanon.repository.NetworkRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("superK")
@Controller
public class SuperKController {
    @RequestMapping("get/{k}")
    @ResponseBody
    public SuperK getSuperK(@PathVariable int k){
        SuperK superK = new SuperK(NetworkRepository.netWork, k);
        superK.run();
        return superK;
    }
}
