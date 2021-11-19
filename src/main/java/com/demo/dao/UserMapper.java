package com.demo.dao;

import com.demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    // Get user by id
    User getUserById(@Param("userId") int userId);

    // Get user by name
    User getUserByName(@Param("userName") String userName);


    // Add User
    int addUser(User user);

    // Update User
    int updateUser(User user);

    // Update avator
    int updateAvatar(@Param("userId") int userId,@Param("url") String imageUrl);

    // Count User
    int countUser();



}
