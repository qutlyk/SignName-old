package com.lihuanyu.signin.controller;

import com.google.gson.Gson;
import com.lihuanyu.signin.config.ProductConfig;
import com.lihuanyu.signin.model.SignListDao;
import com.lihuanyu.signin.service.GetRealMessage;
import com.lihuanyu.signin.util.SessionUser;
import com.lihuanyu.signin.util.MCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by skyADMIN on 16/3/4.
 */
@Controller
public class IndexController {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private SignListDao signListDao;

    @Autowired
    private GetRealMessage getRealMessage;

    @RequestMapping(value = "/", method = RequestMethod.GET, params = "verify_request")
    public String testString(String verify_request, Model model) throws Exception {
        MCrypt mCrypt = new MCrypt();
        String output = new String(mCrypt.decrypt(verify_request));
        Gson gson = new Gson();
        try {
            SessionUser sessionUser = gson.fromJson(output, SessionUser.class);
            httpSession.setAttribute("username", sessionUser.visit_user.username);
            String result = getRealMessage.getMessage(sessionUser.visit_oauth.access_token);
            int yibanid = sessionUser.visit_user.userid;
            String yibanname = sessionUser.visit_user.username;
            String ans = getRealMessage.ProcessSign(result, yibanid, yibanname);

            if (ans.equals("success")) {
                return "redirect:/success";
            } else if (ans.equals("error")) {
                model.addAttribute("result", "无法获取真实信息,是否未通过实名认证.");
                return "redirect:/error";
            } else {
                return "redirect:/false";
            }
        }catch (Exception ex){
            ex.printStackTrace();
            model.addAttribute("result","授权失败,原因不明,请联系石大易班发展中心.");
            return "redirect:/error";
        }
    }

    @RequestMapping("/success")
    public String success(Model  model){
        String headurl = (String) httpSession.getAttribute("yibanhead");
        String username = (String) httpSession.getAttribute("username");
        model.addAttribute("result","成功签到");
        model.addAttribute("username",username);
        model.addAttribute("photo",headurl);
        model.addAttribute("word","感谢您按时参会");
        return "index";
    }

    @RequestMapping("/false")
    public String fail(Model model){
        String headurl = (String) httpSession.getAttribute("yibanhead");
        String username = (String) httpSession.getAttribute("username");
        model.addAttribute("result","已经签到");
        model.addAttribute("username",username);
        model.addAttribute("photo",headurl);
        model.addAttribute("word","已经签过到了,请勿重复签到");
        return "index";
    }

    @RequestMapping("/")
    public ModelAndView oauth(){
        return new ModelAndView("redirect:https://openapi.yiban.cn/oauth/authorize?client_id=" + ProductConfig.client_id + "&redirect_uri=" + ProductConfig.redirect_uri);
    }
}
