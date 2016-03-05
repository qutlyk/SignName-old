package com.lihuanyu.signin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by skyADMIN on 16/3/4.
 */
@Controller
public class AdminController {

    @RequestMapping("/admin")
    public String showAdminLogin(){
        return "adminlogin";
    }
}
