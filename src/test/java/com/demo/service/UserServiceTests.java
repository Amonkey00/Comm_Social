package com.demo.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.demo.pojo.User;
import com.demo.utils.JWTHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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

    @Test
    public void JwtTest(){
        User user = userService.getUserById(1);
        String token =JWTHelper.getToken(user.getUserName(),user.getKeyword());
        System.out.println(token);
        //System.out.println(JWT.decode(token).getAudience().get(0));
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getKeyword())).build();
        //System.out.println(jwtVerifier.verify(token));
    }

}
