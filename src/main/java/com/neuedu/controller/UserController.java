package com.neuedu.controller;

import com.neuedu.consts.Const;
import com.neuedu.exception.MyException;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;


@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    IUserService userService;

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(HttpServletRequest request,HttpServletResponse response){
        HttpSession session=request.getSession();
        //从cookie中获取用户名和密码
        Cookie[] cookies = request.getCookies();
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
                return "home/login";
            session.setAttribute(Const.CURRENT_USER,userInfo1);
            return "home/home";
        }
        return "home/login";
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    //传来的内容自动为对象中属性赋值
    public String login(UserInfo userInfo,HttpServletRequest request,HttpServletResponse response){

        HttpSession session=request.getSession();

        UserInfo userInfo1 = userService.login(userInfo);
        session.setAttribute(Const.CURRENT_USER,userInfo1);

        //创建cookie
        Cookie username_cookie=new Cookie("username",userInfo1.getUsername());
        Cookie password_cookie=new Cookie("password",userInfo1.getPassword());
        username_cookie.setMaxAge(7*24*60*60);
        password_cookie.setMaxAge(7*24*60*60);
        response.addCookie(username_cookie);
        response.addCookie(password_cookie);

        return "home/home";
    }

    //显示用户列表
    @RequestMapping(value = "userinfo/list")
    public String findAll(HttpSession session){
        List<UserInfo> userList = userService.findAll();
        session.setAttribute("userlist",userList);

        return "user/list";
    }


    //跳转用户更新列表
    @RequestMapping(value = "userinfo/update/{id}",method = RequestMethod.GET)
    public String updateuser(@PathVariable("id") Integer userId, HttpServletRequest request){
        UserInfo userInfo = userService.findUserById(userId);
        request.setAttribute("userinfo",userInfo);
        return "user/index";
    }
    //跳转用户添加界面
    @RequestMapping(value = "userinfo/insert",method = RequestMethod.GET)
    public String insert(){
        return "user/index";
    }


    //用户更新/添加提交
    @RequestMapping(value = "userinfo/updateorinsert",method = RequestMethod.POST)
    public String updateuser(UserInfo userInfo, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        if(userInfo.getId()!=null){
            int count = userService.updateUser(userInfo);
            if(count>0){
                return "redirect:/user/userinfo/list";
            }
            return "user/index";
        }
        int count = userService.addUserInfo(userInfo);
        if(count>0){
            return "redirect:/user/userinfo/list";
        }
        throw new MyException("添加失败");
    }

    //用户删除操作
    @RequestMapping(value = "userinfo/delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable("id") Integer id, HttpServletRequest request){
        int count = userService.deleteUserInfo(id);
        if(count>0){
            return "redirect:/user/userinfo/list";
        }
        throw new MyException("删除失败");
    }







}
