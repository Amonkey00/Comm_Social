package com.demo.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.config.WebSocketConfig;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@ServerEndpoint("/websocket/{usernames}")
@RestController
public class SocketController {

    public static int onlineNUmber = 0;

    private static Map<String, SocketController> clients = new HashMap<>();

    private Session session;

    private String username;

    private String toUsername;

    @RequestMapping("/websocket")
    public String webSocket(){
        return "See!";
    }

    @OnOpen
    public void onOpen(@PathParam("usernames") String usernames,Session session){
        System.out.println(username+" comes");
        String[] names = usernames.split("&");
        this.username = names[0];
        this.session = session;
        this.toUsername = names[1];
        if(!clients.containsKey(username))
        {
            onlineNUmber++;
            clients.put(username,this);
        }
        try{
//            Set<String> lists = clients.keySet();
//
//            //To tell friend you are online;
//            //MessageType: 1-online 2-offline 3-check online 4-normal message
//            Map<String,Object> mp1 = new HashMap<>();
//            mp1.put("onlineUsers",lists);
//            mp1.put("username",username);
//            mp1.put("messageType",1);
            sendMessageTo("hello!",toUsername);

            //To tell yourself your friend is online or not;
            checkOnline(username,toUsername);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(){
        System.out.println(username+" leaves");
        if(clients.containsKey(username)){
            onlineNUmber--;
            clients.remove(username);
        }
        try{
//            Set<String> lists = clients.keySet();
//            Map<String,Object> mp1 = new HashMap<>();
//            mp1.put("onlineUsers",lists);
//            mp1.put("username",username);
//            mp1.put("messageType",2);
            sendMessageTo("bye!",toUsername);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public void onMessage(String message, Session session){
        try{
            JSONObject jsonObject = JSON.parseObject(message);
            String textMessage = jsonObject.getString("message");
            String fromUsername = jsonObject.getString("username");
            String targetName = jsonObject.getString("targetName");

            sendMessageTo(textMessage,targetName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessageTo(String message,String targetName) throws IOException {
        for(SocketController item:clients.values()){
            if(item.username.equals(targetName)){
                item.session.getBasicRemote().sendText(message);
                break;
            }
        }
    }

    public void checkOnline(String username,String checkName) throws IOException {
        SocketController userSocket= clients.get(username);
        SocketController checkSocket = clients.getOrDefault(checkName,null);
        if(checkSocket==null){
            userSocket.session.getBasicRemote().sendText(checkName+" is offline");
        }
        else{
            userSocket.session.getBasicRemote().sendText(checkName + "is online");
        }

    }


    public static synchronized  int getOnlineNUmber(){
        return onlineNUmber;
    }


}
