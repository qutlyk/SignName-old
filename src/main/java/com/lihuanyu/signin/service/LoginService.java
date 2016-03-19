package com.lihuanyu.signin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * Created by skyADMIN on 16/3/19.
 */
@Service
public class LoginService {

    @Autowired
    private HttpSession httpSession;

    public boolean isLogin() {
        if (httpSession.getAttribute("userid") != null) {
            return true;
        }
        return false;
    }

    public String toLogin(){

        return "error";
    }
}
