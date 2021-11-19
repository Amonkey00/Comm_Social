package com.demo.dao;

import com.demo.pojo.Friend;
import com.demo.pojo.Page;
import com.demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FriendMapper {

    List<User> getFriendByUserId(Page page);

    Friend getFriendByIds(@Param("fromId")int fromId,@Param("toId")int toId);

    int addFriend(@Param("fromId")int fromId,@Param("toId")int toId);

    int deleteFriend(@Param("fromId")int fromId,@Param("toId")int toId);

    int countFriendByUserId(@Param("userId") int userId);
}
