package com.lihuanyu.signin.service;

import com.google.gson.Gson;
import com.lihuanyu.signin.model.SignList;
import com.lihuanyu.signin.model.SignListDao;
import com.lihuanyu.signin.session.RealUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by skyADMIN on 16/3/4.
 */
@Service
public class GetRealMessage {

    @Autowired
    private SignListDao signListDao;

    public String getMessage(String access_token) throws IOException {
        String url = "https://openapi.yiban.cn/user/real_me";
        String charset = "UTF-8";  // Or in Java 7 and later, use the constant: java.nio.charset.StandardCharsets.UTF_8.name()

        String query = String.format("access_token=%s",
                URLEncoder.encode(access_token, charset));

        URLConnection connection = new URL(url + "?" + query).openConnection();
        connection.setRequestProperty("Accept-Charset", charset);
        InputStream response = connection.getInputStream();
        StringBuilder sb=new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(response));
        String read;

        while((read=br.readLine()) != null) {
            sb.append(read);
        }

        br.close();

        return sb.toString();
    }

    public String ProcessSign(String json, int yibanid, String yibanname){
        Gson gson = new Gson();
        RealUserInfo realUserInfo = gson.fromJson(json, RealUserInfo.class);
        Collection<SignList> signLists = signListDao.findByYibanid(yibanid);
        if (signLists.isEmpty()){
            SignList signList = new SignList();
            signList.setYibanid(yibanid);
            signList.setRealname(realUserInfo.info.yb_realname);
            signList.setYibanname(yibanname);
            signList.setSigned_time(new Timestamp(System.currentTimeMillis()));
            signListDao.save(signList);
            return "success";
        }else {
            return "false";
        }
    }
}
