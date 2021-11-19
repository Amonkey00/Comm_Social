package com.demo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class JWTHelper {
    public static String getToken(String userName,String password){
        String token = JWT.create().withAudience(userName)
                .sign(Algorithm.HMAC256(password));
        return token;
    }
}
