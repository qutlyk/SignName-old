package com.lihuanyu.signin.controller;

import com.lihuanyu.signin.model.CreateList;
import com.lihuanyu.signin.model.CreateListDao;
import com.lihuanyu.signin.model.SignList;
import com.lihuanyu.signin.service.GetRealMessage;
import com.lihuanyu.signin.service.LoginService;
import com.lihuanyu.signin.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

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

    @Autowired
    private GetRealMessage getRealMessage;

    @RequestMapping("/confirmsign")
    public String doSign() throws IOException {
        if (!loginService.isLogin()) {
            return loginService.toYibanAuth();
        }
        if (httpSession.getAttribute("signid") == null) {
            return "error";
        }
        long signid = (long) httpSession.getAttribute("signid");
        httpSession.removeAttribute("signid");
        int yibanid = (int) httpSession.getAttribute("userid");
        String yibanname = (String) httpSession.getAttribute("username");
        String msg = getRealMessage.getMessage((String) httpSession.getAttribute("access_token"));
        return "redirect:/" + getRealMessage.ProcessSign(msg, yibanid, yibanname, (int) signid);
    }

    @RequestMapping("/signdetail")
    public String showSignDetail(int id, Model model) {
        if (!loginService.isLogin()) {
            return loginService.toYibanAuth();
        }
        CreateList createList = createListDao.findOne((long) id);
        int yibanid = (int) httpSession.getAttribute("userid");
        if (createList.getYibanid() != yibanid) {
            return "error";
        }
        Collection<SignList> signLists = signService.getSignList(id);
        model.addAttribute("signLists", signLists);
        model.addAttribute("number", signLists.size());
        String src = "http://localhost:8082/sign?id=" + id;
        String picsrc = "http://qr.topscan.com/api.php?text=" + src;
        model.addAttribute("picsrc", picsrc);
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
    public String doCreate(String actname, Model model) {
        return signService.doCreate(actname, model);
    }

    @RequestMapping("/sign")
    public String showSign(long id, Model model) {
        if (!loginService.isLogin()) {
            httpSession.setAttribute("signid", id);
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
