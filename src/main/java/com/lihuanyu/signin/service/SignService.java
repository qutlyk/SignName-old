package com.lihuanyu.signin.service;

import com.lihuanyu.signin.model.CreateList;
import com.lihuanyu.signin.model.CreateListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

/**
 * Created by skyADMIN on 16/5/7.
 */
@Service
public class SignService {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private CreateListDao createListDao;

    public String doCreate(String actName) {
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
        return "index";
    }

    public String signResult(Model model, String result, String word) {
        String headurl = (String) httpSession.getAttribute("yibanhead");
        String username = (String) httpSession.getAttribute("username");
        model.addAttribute("result", result);
        model.addAttribute("username", username);
        model.addAttribute("photo", headurl);
        model.addAttribute("word", word);
        return "index";
    }
}
