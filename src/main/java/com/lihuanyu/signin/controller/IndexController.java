package com.lihuanyu.signin.controller;

import com.lihuanyu.signin.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by skyADMIN on 16/3/4.
 */
@Controller
public class IndexController {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private LoginService loginService;

    @RequestMapping("/")
    public ModelAndView showindex(){
        if (!loginService.isLogin()){
            return loginService.toYibanAuth();
        }
        return new ModelAndView("index");
    }
}
