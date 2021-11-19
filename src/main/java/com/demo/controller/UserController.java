package com.demo.controller;

import com.demo.pojo.Friend;
import com.demo.pojo.Page;
import com.demo.pojo.User;
import com.demo.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Value("${path.default-avatar-path}")
    private String defaultAvatarPath;

    @PostMapping("/login")
    public String login(ServletRequest request){
        String userName =  request.getParameter("userName");
        String password =  request.getParameter("keyword");
        System.out.println(userName);
        System.out.println(password);
        if(userService.login(userName,password)>0) return "Success";
        return "Failed";
    }

    @PostMapping("/register")
    public String register(ServletRequest request){
        String userName = request.getParameter("userName");
        if(userService.checkUserExistByName(userName))return "User exist";
        String password = request.getParameter("keyword");
        String mail = request.getParameter("mail");
        String introduction = request.getParameter("introduction");
        int status=userService.register(userName,password,mail,introduction);
        return status>0 ? "Success" : "Failed";
    }

    @GetMapping("/{userId}")
    public String getUserInfo(@PathVariable("userId") int userId) throws JsonProcessingException {
        User user = userService.getUserById(userId);
        if(user==null)return "No such user";
        ObjectMapper jsonMapper = new ObjectMapper();
        return jsonMapper.writeValueAsString(user);
    }

    @PostMapping("/update")
    public String updateUserInfo(ServletRequest request){
        int userId = Integer.parseInt(request.getParameter("userId"));
        String userName = request.getParameter("userName");
        String mail = request.getParameter("mail");
        String introduction = request.getParameter("introduction");

        User user = userService.getUserById(userId);
        user.setUserName(userName);
        user.setMail(mail);
        user.setIntroduction(introduction);

        User newNameUser = userService.getUserByName(user.getUserName());
        if(newNameUser!=null&&newNameUser.getUserId()!=user.getUserId())return "Name is used";
        int status = userService.updateUserInfo(user);
        return status>0 ? "Success" : "Failed";
    }

    @PostMapping("/uploadAvatar")
    public String uploadImage(MultipartHttpServletRequest request) throws IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String path = request.getServletContext().getRealPath(defaultAvatarPath);
        File realPath = new File(path);
        if(!realPath.exists()){
            realPath.mkdir();
        }

        MultipartFile avatarFile = request.getFile("image");
        if(avatarFile==null) return "No image file selected";
        String filename = avatarFile.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf("."));
        String imageUrl = realPath + "\\" + userId + "_image" + suffix;
        try {
            avatarFile.transferTo(new File(imageUrl));
        }catch (Exception e){
            e.printStackTrace();
        }

        int status = userService.uploadAvatar(userId,imageUrl);
        return status>0? "Success":"Failed";
    }

    @GetMapping("friends/{userId}")
    public String getFriendsInfo(@PathVariable("userId") int userId,ServletRequest request) throws JsonProcessingException {
        User user = userService.getUserById(userId);
        if(user==null)return "No such user";

        // Page handle
        int start = Integer.parseInt(request.getParameter("start"));
        int count = Integer.parseInt(request.getParameter("count"));
        int total = userService.countFriendById(userId);

        Page page = new Page((start-1)*count,count);
        page.setTotal(total);
        page.setId(userId);
        List<User> friends = userService.getFriendById(page);
        ObjectMapper jsonMapper = new ObjectMapper();
        System.out.println(page.getTotalPage());
        return jsonMapper.writeValueAsString(friends);
    }

    @PostMapping("/friends/delete")
    public String deleteFriend(ServletRequest request){
        int fromId = Integer.parseInt(request.getParameter("fromId"));
        int toId = Integer.parseInt(request.getParameter("toId"));

        if(!userService.checkFriendByIds(fromId,toId)) {
            return "No such friend";
        }

        int status = userService.deleteFriend(fromId,toId);
        return status>0 ? "Success" : "Failed";
    }

    @PostMapping("/friends/add")
    public String addFriend(ServletRequest request){
        int fromId = Integer.parseInt(request.getParameter("fromId"));
        int toId = Integer.parseInt(request.getParameter("toId"));

        if(userService.checkFriendByIds(fromId,toId)) {
            return "Friend exists.";
        }

        int status = userService.addFriend(fromId,toId);
        return status>0 ? "Success" : "Failed";
    }

    @PostMapping("/wallet/recharge")
    public String rechargeMoney(ServletRequest request){
        int userId = Integer.parseInt(request.getParameter("userId"));
        int amount = Integer.parseInt(request.getParameter("amount"));
        int status = userService.rechargeMoney(userId,amount);
        return status>0 ? "Success" : "Failed";
    }

    @PostMapping("/wallet/reduce")
    public String reduceMoney(ServletRequest request){
        int userId = Integer.parseInt(request.getParameter("userId"));
        int amount = Integer.parseInt(request.getParameter("amount"));
        int status = userService.reduceMoney(userId,amount);
        if(status>0) return "Success";
        if(status==-2) return "Money is not enough";
        return "Failed";
    }








}
