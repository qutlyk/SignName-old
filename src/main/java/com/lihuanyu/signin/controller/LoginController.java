package com.lihuanyu.signin.controller;

import com.lihuanyu.signin.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by skyADMIN on 16/5/6.
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/yibanauth")
    public ModelAndView auth(){
        return loginService.toYibanAuth();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, params = "verify_request")
    public String processAuth(String verify_request) throws Exception {
        return loginService.processAuth(verify_request);
    }


}
