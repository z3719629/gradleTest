package com.example.demo.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Created by Administrator on 2018/6/4.
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/user")
    @ResponseBody
    public Principal user(Principal user){
        return user;
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello";
    }

    @RequestMapping("/safe")
    @ResponseBody
    public String safe(){
        return "safe";
    }

}
