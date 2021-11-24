package com.demo.service;

import com.demo.pojo.Blog;
import com.demo.pojo.Comment;
import com.demo.pojo.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService{

    @Override
    public int postBlog(Blog blog) {


        return 0;
    }

    @Override
    public int deleteBlog(int blogId) {
        return 0;
    }

    @Override
    public int updateBlog(Blog blog) {
        return 0;
    }

    @Override
    public List<Blog> getBlog(Page page) {
        return null;
    }

    @Override
    public List<Blog> getBlogById(Page page, int blogId) {
        return null;
    }

    @Override
    public int postComment(Comment comment) {
        return 0;
    }

    @Override
    public int deleteComment(int commentId) {
        return 0;
    }

    @Override
    public int updateComment(Comment comment) {
        return 0;
    }

    @Override
    public List<Comment> getCommentById(int blogId) {
        return null;
    }
}
