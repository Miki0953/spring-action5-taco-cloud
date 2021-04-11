package com.sensible.tacocloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页功能控制器类
 * 当使用了WebConfig类后这个控制器就不用了，可以删除，
 * 但是为了学习笔记的完整性我们将其保留
 */
@Controller
@RequestMapping("/taco")
public class HomeController {

    @GetMapping
    public String home(){
        return "home";
    }
}
