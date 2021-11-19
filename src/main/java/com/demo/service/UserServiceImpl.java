package com.demo.service;

import com.demo.dao.FriendMapper;
import com.demo.dao.RecordMapper;
import com.demo.dao.UserMapper;
import com.demo.pojo.Friend;
import com.demo.pojo.Page;
import com.demo.pojo.Record;
import com.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private FriendMapper friendMapper;


    @Override
    public User getUserByName(String userName){
        return userMapper.getUserByName(userName);
    }

    @Override
    public int login(String userName, String password) {

        // Get user with userName
        User user = userMapper.getUserByName(userName);
        // Check account and validate password
        if(user==null||!user.getKeyword().equals(password))return -1;
        return 1;
    }

    @Override
    public int register(String userName,String password,String mail,String introduction) {

        // UserId increase by 1
        int currentId = userMapper.countUser()+1;
        User user = new User(currentId,userName,password,mail,"null",introduction,0);

        // check transaction status
        return userMapper.addUser(user);
    }

    @Override
    public int uploadAvatar(int userId, String url) {
        return userMapper.updateAvatar(userId,url);
    }

    @Override
    public int rechargeMoney(int userId, int amount) {
        if(amount<=0) return -1;

        User user = userMapper.getUserById(userId);
        user.setMoney(user.getMoney()+amount);
        if(userMapper.updateUser(user)<0) return -1;

        int currentId = recordMapper.countRecord()+1;
        Record record = new Record(currentId,userId,new Date(),amount);
        return recordMapper.addRecord(record);
    }

    @Override
    public int reduceMoney(int userId, int amount) {
        if(amount<0) return -1;

        User user = userMapper.getUserById(userId);
        if(user.getMoney()<amount)return -2;
        user.setMoney(user.getMoney()-amount);
        if(userMapper.updateUser(user)<0) return -1;

        int currentId = recordMapper.countRecord()+1;
        Record record = new Record(currentId,userId,new Date(),-amount);
        return recordMapper.addRecord(record);
    }

    @Override
    public boolean checkFriendByIds(int userId, int toUserId) {
        return friendMapper.getFriendByIds(userId,toUserId)!=null;
    }

    @Override
    public boolean checkUserExistByName(String userName) {
        return userMapper.getUserByName(userName)!=null;
    }


    @Override
    public int addFriend(int userId, int toUserId) {
        return friendMapper.addFriend(userId,toUserId);
    }

    @Override
    public int deleteFriend(int userId, int toUserId) {
        return friendMapper.deleteFriend(userId,toUserId);
    }

    @Override
    public int updateUserInfo(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public User getUserById(int userId) {
        return userMapper.getUserById(userId);
    }

    @Override
    public List<User> getFriendById(Page page) {

        return friendMapper.getFriendByUserId(page);
    }

    @Override
    public int countFriendById(int userId) {
        return friendMapper.countFriendByUserId(userId);
    }
}
