package com.demo.dao;

import com.demo.pojo.Blog;
import com.demo.pojo.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogMapper {

    int postBlog(Blog blog);

    //删除博客注意配合删除对应评论。
    int deleteBlog(@Param("blogId")int blogId);

    int updateBlog(Blog blog);

    //获取所有人的博客
    List<Blog> getBlog(Page page);

    //获取特定人的博客
    List<Blog> getBlogById(Page page,@Param("blogId")int blogId);



}
