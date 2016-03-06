package com.lihuanyu.signin.service;

import com.lihuanyu.signin.util.RealUserInfo;
import com.lihuanyu.signin.util.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * Created by skyADMIN on 16/3/5.
 */
@Service
public class SaveSession {

    @Autowired
    private HttpSession httpSession;

    public void saveBasicInfo(SessionUser sessionUser){
        httpSession.setAttribute("access_token",sessionUser.visit_oauth.access_token);
        httpSession.setAttribute("userid",sessionUser.visit_user.userid);
    }

    public void saveRealInfo(RealUserInfo realUserInfo){
        httpSession.setAttribute("realname",realUserInfo.info.yb_realname);
        httpSession.setAttribute("yibanhead",realUserInfo.info.yb_userhead);
    }
}
