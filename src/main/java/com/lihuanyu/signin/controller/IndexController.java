package com.lihuanyu.signin.controller;

import com.lihuanyu.signin.service.LoginService;
import com.lihuanyu.signin.service.SignService;
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

    @Autowired
    private SignService signService;

    @RequestMapping("/")
    public String showindex(Model model){
        if (!loginService.isLogin()){
            return loginService.toYibanAuth();
        }
        if (httpSession.getAttribute("signid")!=null){
            return "redirect:/sign?id="+(long)httpSession.getAttribute("signid");
        }
        int yibanid = (int) httpSession.getAttribute("userid");
        model.addAttribute("lists",signService.getCreateList(yibanid));
        return "index";
    }
}
