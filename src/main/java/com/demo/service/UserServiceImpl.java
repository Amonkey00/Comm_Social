package com.demo.service;

import com.demo.dao.UserMapper;
import com.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public int login(String userName, String password) {
        User user = userMapper.getUserByName(userName);
        if(user==null||user.getKeyword().equals(password))return -1;
        return 1;
    }

    @Override
    public int register(String userName,String password,String mail,String introduction) {
        User user = userMapper.getUserByName(userName);
        if(user!=null)return -1;
        // UserId increase by 1
        user = new User(102,userName,password,mail,"null",introduction,0);
        if(userMapper.addUser(user)<0)return -1;
        return 1;
    }

    @Override
    public int uploadAvatar(int userId, String url) {
        return 0;
    }

    @Override
    public int rechargeMoney(int userId, int amount) {
        return 0;
    }

    @Override
    public int reduceMoney(int userId, int amount) {
        return 0;
    }

    @Override
    public int addFriend(int userId, int toUserId) {
        return 0;
    }

    @Override
    public int deleteFriend(int userId, int toUserId) {
        return 0;
    }
}
