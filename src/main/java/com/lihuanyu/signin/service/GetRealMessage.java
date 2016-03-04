package com.lihuanyu.signin.service;

import com.google.gson.Gson;
import com.lihuanyu.signin.session.RealUserInfo;
import com.lihuanyu.signin.session.SessionUser;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by skyADMIN on 16/3/4.
 */
@Service
public class GetRealMessage {
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

    public void ProcessSign(String json){
        Gson gson = new Gson();
        RealUserInfo realUserInfo = gson.fromJson(json, RealUserInfo.class);
        System.out.println(realUserInfo.info.yb_realname);
    }
}
