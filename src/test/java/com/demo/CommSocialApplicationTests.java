package com.demo;

import com.demo.dao.GoodMapper;
import com.demo.dao.OrderMapper;
import com.demo.pojo.Good;
import com.demo.pojo.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class CommSocialApplicationTests {

    @Autowired
    GoodMapper goodMapper;

    @Autowired
    OrderMapper orderMapper;

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

}
