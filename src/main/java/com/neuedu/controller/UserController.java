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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;


@Controller
@RequestMapping("/user/userinfo/")
public class UserController {

    @Autowired
    IUserService userService;

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(){
        return "home/login";
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    //传来的内容自动为对象中属性赋值
    public String login(UserInfo userInfo, HttpSession session){

        UserInfo userInfo1 = userService.login(userInfo);
        System.out.println(userInfo1);

        session.setAttribute(Const.CURRENT_USER,userInfo1);
        return "home/home";
    }

    //显示用户列表
    @RequestMapping(value = "list")
    public String findAll(HttpSession session){
        List<UserInfo> userList = userService.findAll();
        System.out.println(userList);
        session.setAttribute("userlist",userList);

        return "user/list";
    }


    //跳转用户更新列表
    @RequestMapping(value = "update/{id}",method = RequestMethod.GET)
    public String updateuser(@PathVariable("id") Integer userId, HttpServletRequest request){
        UserInfo userInfo = userService.findUserById(userId);
        request.setAttribute("userinfo",userInfo);
        return "user/index";
    }
    //跳转用户添加界面
    @RequestMapping(value = "insert",method = RequestMethod.GET)
    public String insert(){
        return "user/index";
    }


    //用户更新/添加提交
    @RequestMapping(value = "updateorinsert",method = RequestMethod.POST)
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
    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable("id") Integer id, HttpServletRequest request){
        int count = userService.deleteUserInfo(id);
        if(count>0){
            return "redirect:/user/userinfo/list";
        }
        throw new MyException("删除失败");
    }







}
