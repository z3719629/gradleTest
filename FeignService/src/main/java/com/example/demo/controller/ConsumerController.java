package com.example.demo.controller;

import com.example.demo.interfaces.ComputeClient;
import com.example.demo.interfaces.ComputeClientSecond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/5/28.
 */
@RestController
@RefreshScope
public class ConsumerController {

    @Autowired
    ComputeClient computeClient;

    @Autowired
    ComputeClientSecond computeClientSecond;

    @Value("${foo}")
    String foo;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public Integer add() {
        return computeClient.add(10, 20);
    }

    @RequestMapping(value = "/sub", method = RequestMethod.GET)
    public Integer sub() {
        return computeClientSecond.sub(30, 20);
    }

    @RequestMapping(value = "/foo", method = RequestMethod.GET)
    public String foo() {
        return foo;
    }

}
