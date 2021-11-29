package com.demo.service;

import com.alibaba.fastjson.JSONArray;
import com.demo.dao.BlogMapper;
import com.demo.dao.CommentMapper;
import com.demo.dao.UserMapper;
import com.demo.pojo.Blog;
import com.demo.pojo.Comment;
import com.demo.pojo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService{

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommentMapper commentMapper;

    /**
     * 返回插入blog的新id
     * @param userId
     * @param blogTitle
     * @param blogContext
     * @return
     */
    @Override
    public int postBlog(int userId,String blogTitle,String blogContext) {

        Date currentTime = new Date(new java.util.Date().getTime());
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
//        String dateString = dateFormat.format(currentTime);
//        ParsePosition pos = new ParsePosition(8);
//        Date currentTime_2 = dateFormat.parse(dateString, pos);
        int blogId=blogMapper.countBlog()+1;
        Blog blog=new Blog();
//        blog.setBlogId(blogId);
        blog.setUserId(userId);
        blog.setBlogTitle(blogTitle);
        blog.setBlogContext(blogContext);
        blog.setBlogDate(currentTime);
        int blogIdnew=blogMapper.postBlog(blog);
        return blogId;
    }

    @Override
    public int deleteBlog(int blogId) {
        //注意先删除评论。
        commentMapper.deleteCommentAll(blogId);
        blogMapper.deleteBlog(blogId);

        return 0;
    }

    @Override
    public int updateBlog(int blogId,String blogTitle,String blogContext) {

        Date currentTime = new Date(new java.util.Date().getTime());
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        //缺省值表示不去修改。
        Blog blog=new Blog(blogId,0,blogTitle,blogContext,currentTime,0);
        blogMapper.updateBlog(blog);
        return 0;
    }

    @Override
    public JSONArray getBlog(int start,int count) {
        Page page=new Page(start,count);
        List<Object[]> result=blogMapper.getBlog(page);
        JSONArray temp=new JSONArray();
        temp.addAll(result);
        return temp;
    }

    @Override
    public JSONArray getBlogById(int start,int count, int userId) {

        Page page=new Page(start,count);
        List<Object[]> result=blogMapper.getBlogById(page, userId);

        JSONArray temp=new JSONArray();
        temp.addAll(result);
        return temp;
    }



    @Override
    public int countBlog(){
        return blogMapper.countBlog();
    }

    @Override
    public int countBlogById(int userId){
        return blogMapper.countBlogById(userId);
    }

    @Override
    public int postComment(int userId,int blogId,String commentContext) {
        int commentId=commentMapper.countComment() + 1;

        Comment comment=new Comment();
        comment.setUserId(userId);
        comment.setBlogId(blogId);
        comment.setCommentContext(commentContext);
        commentMapper.postComment(comment);
        return commentId;
    }

    @Override
    public int deleteComment(int commentId) {
        commentMapper.deleteComment(commentId);
        return 0;
    }

    @Override
    public int deleteCommentAll(int blogId) {
        commentMapper.deleteCommentAll(blogId);
        return 0;
    }


    @Override
    public int updateComment(int commentId,String commentContext) {
        Comment comment=new Comment(commentId,0,0,commentContext);
        commentMapper.updateComment(comment);
        return 0;
    }

    @Override
    public JSONArray getCommentById(int blogId) {
        List<Object> result=commentMapper.getCommentById(blogId);
        JSONArray temp=new JSONArray();
        return temp;
    }
}
