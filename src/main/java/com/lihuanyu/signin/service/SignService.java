package com.lihuanyu.signin.service;

import com.lihuanyu.signin.model.CreateList;
import com.lihuanyu.signin.model.CreateListDao;
import com.lihuanyu.signin.model.SignList;
import com.lihuanyu.signin.model.SignListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by skyADMIN on 16/5/7.
 */
@Service
public class SignService {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private CreateListDao createListDao;

    @Autowired
    private SignListDao signListDao;

    public Collection<SignList> getSignList(int createid) {
        return signListDao.findByCreateid(createid);
    }

    public Iterable<CreateList> getCreateList(int userid) {
        return createListDao.findByYibanid(userid);
    }

    public String doCreate(String actName, Model model) {
        CreateList createList = new CreateList();
        createList.setActivityname(actName);
        int yibanid = (int) httpSession.getAttribute("userid");
        createList.setYibanid(yibanid);
        createList.setSigned_time(new Timestamp(System.currentTimeMillis()));
        try {
            createListDao.save(createList);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "error";
        }
        String src = "http://yb.upc.edu.cn:8082/sign?id=" + createList.getId();
        String picsrc = "http://qr.topscan.com/api.php?text=" + src;
        model.addAttribute("picsrc", picsrc);
        return "createresult";
    }

    public String signResult(Model model, String result, String word) {
        String headurl = (String) httpSession.getAttribute("yibanhead");
        String username = (String) httpSession.getAttribute("username");
        model.addAttribute("result", result);
        model.addAttribute("username", username);
        model.addAttribute("photo", headurl);
        model.addAttribute("word", word);
        return "signresult";
    }
}
