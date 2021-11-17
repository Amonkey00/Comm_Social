package com.demo.dao;

import com.demo.pojo.Friend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FriendMapper {

    List<Friend> getFriendByUserId(@Param("userId") int userId);
}
