package com.lihuanyu.signin.controller;

import com.lihuanyu.signin.service.LoginService;
import com.lihuanyu.signin.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by skyADMIN on 16/3/19.
 */
@Controller
public class SignController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private SignService signService;

    @RequestMapping("/create")
    public String showCreatePage() {
        if (!loginService.isLogin()){
            return loginService.toYibanAuth();
        }
        return "create";
    }

    @RequestMapping(value = "/docreate",method = RequestMethod.POST)
    public String doCreate(String actname){
        return signService.doCreate(actname);
    }

    @RequestMapping("/sign")
    public String signProcess(String id) {
        if (loginService.isLogin()) {
            //处理签到过程
            return "error";
        } else {
            return "error";
        }
    }

    @RequestMapping("/success")
    public String success(Model model) {
        return signService.signResult(model, "签到成功", "感谢您按时参会.");
    }

    @RequestMapping("/false")
    public String fail(Model model) {
        return signService.signResult(model, "签到失败或已经签到", "请勿重复签到.");
    }
}
