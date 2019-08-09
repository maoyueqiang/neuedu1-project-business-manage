package com.neuedu.interceptors;

import com.neuedu.consts.Const;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class MyInterceptor implements HandlerInterceptor{
    @Autowired
    IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

       HttpSession session = request.getSession();

        //从cookie中获取用户名和密码
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie:cookies){
            System.out.println(cookie.getName()+"====="+cookie.getValue());
        }
        String username=null;
        String password=null;
        if(cookies!=null&&cookies.length>0){
            for(Cookie c:cookies){
                if(c.getName().equals("username"))
                    username=c.getValue();
                if(c.getName().equals("password"))
                    password=c.getValue();
            }
        }
        if(username!=null&&password!=null){
            UserInfo userInfo=new UserInfo();
            userInfo.setUsername(username);
            userInfo.setPassword(password);
            UserInfo userInfo1 = userService.login(userInfo);
            if(userInfo1==null)
                return false;
            session.setAttribute(Const.CURRENT_USER,userInfo1);
            return true;
        }
        return false;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
