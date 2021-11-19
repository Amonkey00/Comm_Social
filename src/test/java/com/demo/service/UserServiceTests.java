package com.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Test
    void getTest(){
        Object user = userService.getUserById(3);

        System.out.println(user.toString());
    }

    @Test
    void loginTest(){
        //System.out.println(userService.login("Ming","root"));
        System.out.println(userService.register("Hahah","haha","891@qq.com","我是真的菜"));
    }

    @Test
    public void pathTest(){
        System.out.println(System.getProperty("user.dir"));
    }

}
