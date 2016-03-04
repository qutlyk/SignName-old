package com.lihuanyu.signin.controller;

import com.google.gson.Gson;
import com.lihuanyu.signin.config.DevConfig;
import com.lihuanyu.signin.model.SignList;
import com.lihuanyu.signin.model.SignListDao;
import com.lihuanyu.signin.session.SessionUser;
import com.lihuanyu.signin.util.MCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by skyADMIN on 16/3/4.
 */
@RestController
public class IndexController {

    @Autowired
    private SignListDao signListDao;

    @RequestMapping(value = "/", method = RequestMethod.GET, params = "verify_request")
    public String testString(String verify_request, Model model) throws Exception {
        MCrypt mCrypt = new MCrypt();
        String output = new String(mCrypt.decrypt(verify_request));
        Gson gson = new Gson();
        SessionUser sessionUser = gson.fromJson(output, SessionUser.class);
        SignList signList = new SignList();
        signList.setYibanid(sessionUser.visit_user.userid);
        signList.setYibanname(sessionUser.visit_user.username);
        signListDao.save(signList);
        model.addAttribute("username",sessionUser.visit_user.username);
        return "已记录";
    }

    @RequestMapping("/")
    public ModelAndView oauth(){
        return new ModelAndView("redirect:https://openapi.yiban.cn/oauth/authorize?client_id=" + DevConfig.client_id + "&redirect_uri=" + DevConfig.redirect_uri);
    }
}
