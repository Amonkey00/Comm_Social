package com.demo.dao;

import com.demo.pojo.Blog;
import com.demo.pojo.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface BlogMapper {

    int postBlog(Blog blog);

    //删除博客注意配合删除对应评论。
    int deleteBlog(@Param("blogId")int blogId);

    int updateBlog(Blog blog);

    //获取所有人的博客
    List<Object[]> getBlog(Page page);

    //获取特定人的博客
    List<Object[]> getBlogById(@Param("page")Page page,@Param("userId")int userId);

    int countBlog();

    int countBlogById(@Param("userId")int userId);

}
