package com.demo;

import com.demo.dao.GoodMapper;
import com.demo.dao.OrderMapper;
import com.demo.pojo.Good;
import com.demo.pojo.Order;
import com.demo.service.BlogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CommSocialApplicationTests {

    @Autowired
    GoodMapper goodMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    BlogService blogService;

    @Test
    void contextLoads() {
        Good good = goodMapper.getGoodById(1);
        good.setGoodId(2);
        good.setGoodName("Code2");
        goodMapper.addGood(good);
        Order order = new Order(1,2,1,new Date());
        orderMapper.addOrder(order);
        System.out.println(orderMapper.getOrderList());
    }



    @Test
    void Blogtest(){
//        blogService.postBlog(1,"123","666666");
//        blogService.postBlog(1,"123","666666");
//        blogService.postBlog(1,"123","666666");
//        blogService.postBlog(2,"123","666666");
//        blogService.postBlog(2,"123","666666");
//        blogService.postBlog(2,"123","666666");
//        System.out.println(blogService.getBlog(0, blogService.countBlog()));
//        System.out.println(
//                blogService.getBlogById(0, blogService.countBlogById(1),1));

        blogService.postComment(1,2,"666666");
        blogService.postComment(1,2,"666666");
        blogService.postComment(1,2,"666666");
        blogService.postComment(1,2,"666666");
        System.out.println(blogService.getCommentById(1));

    }

}
