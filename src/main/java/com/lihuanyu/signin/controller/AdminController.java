package com.lihuanyu.signin.controller;

import com.lihuanyu.signin.config.ProductConfig;
import com.lihuanyu.signin.model.SignList;
import com.lihuanyu.signin.model.SignListDao;
import com.lihuanyu.signin.service.AdminService;
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
    private AdminService adminService;

    @RequestMapping("/admin")
    public String showAdminLogin(){
        return "loginadmin";
    }

    @RequestMapping(value = "/adminlogin",method = RequestMethod.POST)
    public String login(String username, String password, Model model){
        return adminService.adminPanel(username,password,model);
    }
}
