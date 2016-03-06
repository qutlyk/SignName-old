package com.lihuanyu.signin.controller;

import com.lihuanyu.signin.config.ProductConfig;
import com.lihuanyu.signin.model.SignList;
import com.lihuanyu.signin.model.SignListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by skyADMIN on 16/3/4.
 */
@Controller
public class AdminController {

    @Autowired
    private SignListDao signListDao;

    @RequestMapping("/admin")
    public String showAdminLogin(){
        return "loginadmin";
    }

    @RequestMapping(value = "/adminlogin",method = RequestMethod.POST)
    public String login(String username, String password, Model model){
        if (username.equals(ProductConfig.adminUsername)&&password.equals(ProductConfig.adminPassword)) {
            Iterable<SignList> signLists = signListDao.findAll();
            int number = (int) signListDao.count();
            model.addAttribute("number",number);
            model.addAttribute("signLists",signLists);
            return "admin";
        }else {
            String result = "出错了!";
            String word = "账号或密码有误!";
            model.addAttribute("title",result);
            model.addAttribute("word",word);
            return "message";
        }
    }
}
