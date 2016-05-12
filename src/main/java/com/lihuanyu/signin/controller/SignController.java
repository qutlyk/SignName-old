package com.lihuanyu.signin.controller;

import com.lihuanyu.signin.model.CreateList;
import com.lihuanyu.signin.model.CreateListDao;
import com.lihuanyu.signin.service.LoginService;
import com.lihuanyu.signin.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by skyADMIN on 16/3/19.
 */
@Controller
public class SignController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private SignService signService;

    @Autowired
    private CreateListDao createListDao;

    @Autowired
    private HttpSession httpSession;

    @RequestMapping("/confirmsign")
    public String doSign() {
        if (!loginService.isLogin()) {
            return loginService.toYibanAuth();
        }
        if (httpSession.getAttribute("signid")==null){
            return "error";
        }

        return "";
    }

    @RequestMapping("/signdetail")
    public String showSignDetail(int id, Model model) {
        model.addAttribute("signLists", signService.getSignList(id));
        return "admin";
    }

    @RequestMapping("/create")
    public String showCreatePage() {
        if (!loginService.isLogin()) {
            return loginService.toYibanAuth();
        }
        return "create";
    }

    @RequestMapping(value = "/docreate", method = RequestMethod.POST)
    public String doCreate(String actname) {
        return signService.doCreate(actname);
    }

    @RequestMapping("/sign")
    public String showSign(long id, Model model) {
        if (!loginService.isLogin()) {
            httpSession.setAttribute("signid",id);
            return loginService.toYibanAuth();
        }
        CreateList createList = createListDao.findOne(id);
        model.addAttribute("name", createList.getActivityname());
        httpSession.setAttribute("signid", id);
        return "sign";
    }

    @RequestMapping("/success")
    public String success(Model model) {
        return signService.signResult(model, "签到成功", "感谢您按时参会.");
    }

    @RequestMapping("/false")
    public String fail(Model model) {
        return signService.signResult(model, "签到失败或已经签到", "请勿重复签到.");
    }
}
