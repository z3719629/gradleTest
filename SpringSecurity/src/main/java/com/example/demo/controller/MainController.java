package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2018/6/4.
 */
@Controller
public class MainController {

    @RequestMapping("/login")
    public String login(Model model, String status) {
        return "login";
    }

}
