package com.example.demo.controller;

import com.example.demo.interfaces.ComputeClient;
import com.example.demo.interfaces.ComputeClientSecond;
import com.netflix.discovery.EurekaClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by Administrator on 2018/5/28.
 */
@Controller
@Secured({"ROLE_ADMIN"})
@RequestMapping("/test")
public class ConsumerController {

    @Autowired
    ComputeClient computeClient;

    @Autowired
    ComputeClientSecond computeClientSecond;

    @Value("${foo}")
    String foo;

    @RequestMapping("/user")
    @ResponseBody
    public Principal user(Principal user){
        return user;
    }

    @RequestMapping(value = "/hi")
    @ResponseBody
    public String hi(){
        return foo;
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public Integer add() {
        return computeClient.add(10, 20);
    }

    @RequestMapping(value = "/sub")
    @ResponseBody
    public Integer sub() {
        return computeClientSecond.sub(30, 20);
    }

}
