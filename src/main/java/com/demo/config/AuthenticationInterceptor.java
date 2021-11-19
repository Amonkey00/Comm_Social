package com.demo.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.demo.annotations.PassToken;
import com.demo.annotations.UserLoginToken;
import com.demo.pojo.User;
import com.demo.service.UserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@NoArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        // Not a reflect Method
        if(!(handler instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        //check have pass_token
        if(method.isAnnotationPresent(PassToken.class)){
            PassToken passToken = method.getAnnotation(PassToken.class);
            if(passToken.required()){
                return true;
            }
        }
        // check UserLoginToken
        if(method.isAnnotationPresent(UserLoginToken.class)){
            PassToken passToken = method.getAnnotation(PassToken.class);
            if(token==null) {
                throw new RuntimeException("Have not token，please login");
            }
            //acquire token
            String userName="-1";
            try{
                userName = JWT.decode(token).getAudience().get(0);
            }catch(JWTDecodeException e){
                e.printStackTrace();
                throw new RuntimeException("401");
            }
            User user = userService.getUserByName(userName);
            if(user==null){
                throw new RuntimeException("user doesn't exists,please login again");
            }

            // Authenticate token
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getKeyword())).build();
            try{
                jwtVerifier.verify(token);
            }catch (JWTVerificationException e){
                e.printStackTrace();
                throw new RuntimeException("401");
            }
            return true;
        }
        return true;
    }
}
