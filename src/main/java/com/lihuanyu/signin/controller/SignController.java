package com.lihuanyu.signin.controller;

import com.lihuanyu.signin.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by skyADMIN on 16/3/19.
 */
@Controller
public class SignController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/sign")
    public String signProcess(String id){
        if (loginService.isLogin()){
            //处理签到过程
            return "error";
        }else {
            return "error";
        }
    }
}
