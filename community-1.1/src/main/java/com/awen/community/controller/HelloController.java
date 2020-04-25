package com.awen.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Liu Awen Email:willowawen@gmail.com
 * @create 2020-04-24 17:58
 */
@Controller
@RequestMapping("/hello")
public class HelloController {
    @RequestMapping("/spring")
    @ResponseBody
    public String sayHello(){
        return "Hello Spring!!!";
    }
}
