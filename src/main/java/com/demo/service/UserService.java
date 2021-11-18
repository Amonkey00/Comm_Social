package com.demo.service;

import com.demo.pojo.Friend;
import com.demo.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    int login(String userName,String password);

    int register(String userName,String password,String mail,String introduction);

    int uploadAvatar(int userId,String url);

    int rechargeMoney(int userId,int amount);

    int reduceMoney(int userId,int amount);

    int addFriend(int userId,int toUserId);

    int deleteFriend(int userId,int toUserId);

    int updateUserInfo(User user);

    boolean checkFriendByIds(int userId,int toUserId);

    boolean checkUserExistByName(String userName);


    User getUserById(int userId);

    User getUserByName(String userName);


    List<User> getFriendById(int userId);

}
