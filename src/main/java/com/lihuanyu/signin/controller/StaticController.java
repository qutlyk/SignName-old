package com.lihuanyu.signin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by skyADMIN on 16/3/7.
 */
@Controller
public class StaticController {

    @RequestMapping("/about")
    public String showAbout(){
        return "about";
    }

    @RequestMapping("/contact")
    public String showContact(){
        return "contact";
    }
}
