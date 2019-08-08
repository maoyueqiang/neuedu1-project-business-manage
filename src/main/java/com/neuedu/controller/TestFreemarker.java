package com.neuedu.controller;

import com.neuedu.pojo.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class TestFreemarker {

    @RequestMapping("/ftl")
    public String testftl(HttpSession session){
        UserInfo userInfo=new UserInfo();
        userInfo.setUsername("zhangsan");
        session.setAttribute("user",userInfo);
        return "home/home";//web-inf/views home.ftl
    }

}
