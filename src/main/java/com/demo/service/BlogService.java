package com.demo.service;

import com.demo.pojo.Blog;
import com.demo.pojo.Comment;
import com.demo.pojo.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogService {

    int postBlog(Blog blog);

    //删除博客注意配合删除对应评论。
    int deleteBlog(int blogId);

    int updateBlog(Blog blog);

    //获取所有人的博客
    List<Blog> getBlog(Page page);

    //获取特定人的博客
    List<Blog> getBlogById(Page page,int blogId);

    int postComment(Comment comment);

    //删除单个评论
    int deleteComment(int commentId);

    int updateComment(Comment comment);

    //获取一个博客下的所有评论
    List<Comment> getCommentById(int blogId);
}
