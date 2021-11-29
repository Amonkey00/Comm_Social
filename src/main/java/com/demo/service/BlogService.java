package com.demo.service;

import com.alibaba.fastjson.JSONArray;
import com.demo.pojo.Blog;
import com.demo.pojo.Comment;
import com.demo.pojo.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogService {

    int postBlog(int userId,String blogTitle,String blogContext);

    //删除博客注意配合删除对应评论。
    int deleteBlog(int blogId);

    int updateBlog(int blogId,String blogTitle,String blogContext);

    //获取所有人的博客
    JSONArray getBlog(int start,int count);

    //获取特定人的博客
    JSONArray getBlogById(int start,int count,int userId);

    int countBlog();

    int countBlogById(int userId);


    int postComment(int userId,int blogId,String commentContext);

    //删除单个评论
    int deleteComment(int commentId);

    int deleteCommentAll(int blogId);

    int updateComment(int commentId,String commentContext);

    //获取一个博客下的所有评论
    JSONArray getCommentById(int blogId);
}
