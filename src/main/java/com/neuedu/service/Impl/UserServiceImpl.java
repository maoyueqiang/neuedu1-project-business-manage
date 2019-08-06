package com.neuedu.service.Impl;

import com.neuedu.dao.UserInfoMapper;
import com.neuedu.exception.MyException;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public UserInfo login(UserInfo userInfo) throws MyException {

        //参数非空校验
        if(userInfo==null){
            throw new MyException("参数不能为空");
        }
        if(userInfo.getUsername()==null){
            throw new MyException("用户名不能为空");
        }
        if(userInfo.getPassword()==null){
            throw new MyException("密码不能为空");
        }

        //用户名是否存在
        int count = userInfoMapper.existUsername(userInfo.getUsername());
        if(count==0){
            throw new MyException("用户名不存在");
        }

        //根据用户名和密码获得用户
        UserInfo userInfo1= userInfoMapper.findByUsernameAndPassword(userInfo);
        if(userInfo1==null){
            throw new MyException("密码错误");
        }

        //判断权限
        if(userInfo1.getRole()!=0){
            throw new MyException("非管理员用户,无权访问");
        }

        return userInfo1;
    }
}
