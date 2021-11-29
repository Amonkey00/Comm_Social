package com.demo.dao;

import com.demo.pojo.Blog;
import com.demo.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface CommentMapper {

    int postComment(Comment comment);

    //删除单个评论
    int deleteComment(@Param("commentId")int commentId);

    //删除对应博客下的所有评论
    int deleteCommentAll(@Param("blogId")int blogId);

    int updateComment(Comment comment);

    //获取一个博客下的所有评论
    List<Object> getCommentById(@Param("blogId")int blogId);

    int countComment();


}
