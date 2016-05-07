package com.lihuanyu.signin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

/**
 * Created by skyADMIN on 16/5/7.
 */
@Service
public class SignService {

    @Autowired
    private HttpSession httpSession;

    public String signResult(Model model,String result, String word){
        String headurl = (String) httpSession.getAttribute("yibanhead");
        String username = (String) httpSession.getAttribute("username");
        model.addAttribute("result",result);
        model.addAttribute("username",username);
        model.addAttribute("photo",headurl);
        model.addAttribute("word",word);
        return "index";
    }
}
