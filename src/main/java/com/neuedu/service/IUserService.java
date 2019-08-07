package com.neuedu.service;

import com.neuedu.exception.MyException;
import com.neuedu.pojo.UserInfo;

import java.util.List;

public interface IUserService {
    public UserInfo login(UserInfo userInfo) throws MyException;

    public List<UserInfo> findAll() throws MyException;

    public int updateUser(UserInfo userInfo) throws MyException;

    public UserInfo findUserById(int id) throws MyException;

    public int addUserInfo(UserInfo userInfo) throws MyException;

    public int deleteUserInfo(int id) throws MyException;
}
