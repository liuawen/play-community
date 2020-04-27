package com.awen.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Liu Awen Email:willowawen@gmail.com
 * @create 2020-04-26 10:02
 */
@Controller
public class LoginController {
    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String getRegisterPage(){
        return "/site/regiser";
    }
}
