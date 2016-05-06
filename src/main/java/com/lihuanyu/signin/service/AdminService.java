package com.lihuanyu.signin.service;

import com.lihuanyu.signin.config.ProductConfig;
import com.lihuanyu.signin.model.SignList;
import com.lihuanyu.signin.model.SignListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

/**
 * Created by skyADMIN on 16/3/19.
 */
@Service
public class AdminService {

    @Autowired
    private SignListDao signListDao;

    public String adminPanel(String username,String password,Model model){
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
