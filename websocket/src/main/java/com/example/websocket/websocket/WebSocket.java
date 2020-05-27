package com.example.websocket.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Ryan
 * @Date: 2020/5/7 9:23
 * @Version: 1.0
 * @Description:
 */
//    private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    private static int onlineNumber = 0;  // 在线人数
//    private static Map<String, WebSocket> clients = new ConcurrentHashMap<String, WebSocket>(); // 用户名姓名为key，WebSocket为对象保存起来
//    private Session session; // 会话
//    private String userName; // 用户名称
//
//    public void onOpen(@PathParam("username") String userName, Session session) {
//        onlineNumber++;
//        logger.info("正在连接的客户id：" + session.getId() + "用户名：" + userName);
//        this.userName = userName;
//        this.session = session;
//        logger.info("有新连接加入，当前在线人数为：" + onlineNumber);
//        //messageType 1代表上线 2代表下线 3代表在线名单 4代表普通消息
//        //先给所有人发送通知，说我上线了
//        Map<String, Object> map = new HashMap<>();
//        map.put("messageType", 1);
//        map.put("userName", userName);
//        sendMessageAll(JSON.toJSONString(map), userName);
//
//        // 把自己的信息加入到map中去
//        clients.put(userName, this);
//        // 给自己发一条信息，告诉自己有谁在线
//        Map<String, Object> map2 = new HashMap<>();
//        map2.put("messageType", 3);
//        // 移除掉自己
//        Set<String> set = clients.keySet();
//        map2.put("onlineUsers", set);
//        sendMessageTo(JSON.toJSONString(map2), userName);
//    }
//
//    /**
//     * 给所有人发信息说我上线了
//     * @param message
//     * @param formUserName
//     */
//    public void sendMessageAll(String message, String formUserName) {
//        for (WebSocket webSocket : clients.values()) {
//            webSocket.session.getAsyncRemote().sendText(message);
//        }
//    }
//
//    public void sendMessageTo(String message, String toUserName) {
//        for (WebSocket webSocket : clients.values()) {
//            if (webSocket.userName.equals(toUserName)) {
//                webSocket.session.getAsyncRemote().sendText(message);
//                break;
//            }
//        }
//    }
//
//    @OnError
//    public void onError(Session session, Throwable throwable) {
//        logger.info("服务端发生了错误" + throwable.getMessage());
//    }
//
//    /**
//     * 关闭连接
//     */
//    public void onClose() {
//        onlineNumber--;
//        clients.remove(userName);
//        Map<String, Object> map = new HashMap<>();
//        map.put("messageType", 2);
//        map.put("onlineUsers", clients.keySet());
//        map.put("userName", userName);
//        sendMessageAll(JSON.toJSONString(map), userName);
//        System.out.println("有连接关闭，当前在线人数：" + onlineNumber);
//    }
//
//    public void onMessage(String message, Session session) {
//        logger.info("来自客户端的消息：" + message + "客户端的id是：" + session.getId());
//        JSONObject jsonObject = JSON.parseObject(message);
//        String textMessage = jsonObject.getString("message");
//        String fromUserName = jsonObject.getString("userName");
//        String toUserName = jsonObject.getString("to");
//        //如果不是发给所有，那么就发给某一个人
//        //messageType 1代表上线 2代表下线 3代表在线名单  4代表普通消息
//        Map<String, Object> map = new HashMap<>();
//        map.put("messageType", 4);
//        map.put("textMessage", textMessage);
//        map.put("fromUserName", fromUserName);
//        if (toUserName.equals("All")) {
//            map.put("toUserName", "所有人");
//            sendMessageAll(JSON.toJSONString(map), fromUserName);
//        } else {
//            map.put("toUserName", toUserName);
//            sendMessageTo(JSON.toJSONString(map), fromUserName);
//        }
//
//    }

    /**
     * @author 2018年5月18日16:11:48
     */
    @Component
    @ServerEndpoint("/websocket/{username}")
    public class WebSocket {
        private Logger logger = LoggerFactory.getLogger(this.getClass());
        /**
         * 在线人数
         */
        public static int onlineNumber = 0;
        /**
         * 以用户的姓名为key，WebSocket为对象保存起来
         */
        private static Map<String, WebSocket> clients = new ConcurrentHashMap<String, WebSocket>();
        /**
         * 会话
         */
        private Session session;
        /**
         * 用户名称
         */
        private String username;
        /**
         * 建立连接
         *
         * @param session
         */
        @OnOpen
        public void onOpen(@PathParam("username") String username, Session session)
        {
            onlineNumber++;
            logger.info("现在来连接的客户id："+session.getId()+"用户名："+username);
            this.username = username;
            this.session = session;
            logger.info("有新连接加入！ 当前在线人数" + onlineNumber);
            try {
                //messageType 1代表上线 2代表下线 3代表在线名单 4代表普通消息
                //先给所有人发送通知，说我上线了
                Map<String,Object> map1 = Maps.newHashMap();
                map1.put("messageType",1);
                map1.put("username",username);
                sendMessageAll(JSON.toJSONString(map1),username);

                //把自己的信息加入到map当中去
                clients.put(username, this);
                //给自己发一条消息：告诉自己现在都有谁在线
                Map<String,Object> map2 = Maps.newHashMap();
                map2.put("messageType",3);
                //移除掉自己
                Set<String> set = clients.keySet();
                map2.put("onlineUsers",set);
                sendMessageTo(JSON.toJSONString(map2),username);
            }
            catch (IOException e){
                logger.info(username+"上线的时候通知所有人发生了错误");
            }



        }

        @OnError
        public void onError(Session session, Throwable error) {
            logger.info("服务端发生了错误"+error.getMessage());
            //error.printStackTrace();
        }
        /**
         * 连接关闭
         */
        @OnClose
        public void onClose()
        {
            onlineNumber--;
            //webSockets.remove(this);
            clients.remove(username);
            try {
                //messageType 1代表上线 2代表下线 3代表在线名单  4代表普通消息
                Map<String,Object> map1 = Maps.newHashMap();
                map1.put("messageType",2);
                map1.put("onlineUsers",clients.keySet());
                map1.put("username",username);
                sendMessageAll(JSON.toJSONString(map1),username);
            }
            catch (IOException e){
                logger.info(username+"下线的时候通知所有人发生了错误");
            }
            logger.info("有连接关闭！ 当前在线人数" + onlineNumber);
        }

        /**
         * 收到客户端的消息
         *
         * @param message 消息
         * @param session 会话
         */
        @OnMessage
        public void onMessage(String message, Session session)
        {
            try {
                logger.info("来自客户端消息：" + message+"客户端的id是："+session.getId());
                JSONObject jsonObject = JSON.parseObject(message);
                String textMessage = jsonObject.getString("message");
                String fromusername = jsonObject.getString("username");
                String tousername = jsonObject.getString("to");
                //如果不是发给所有，那么就发给某一个人
                //messageType 1代表上线 2代表下线 3代表在线名单  4代表普通消息
                Map<String,Object> map1 = Maps.newHashMap();
                map1.put("messageType",4);
                map1.put("textMessage",textMessage);
                map1.put("fromusername",fromusername);
                if(tousername.equals("All")){
                    map1.put("tousername","所有人");
                    sendMessageAll(JSON.toJSONString(map1),fromusername);
                }
                else{
                    map1.put("tousername",tousername);
                    sendMessageTo(JSON.toJSONString(map1),tousername);
                }
            }
            catch (Exception e){
                logger.info("发生了错误了");
            }

        }


        public void sendMessageTo(String message, String ToUserName) throws IOException {
            for (WebSocket item : clients.values()) {
                if (item.username.equals(ToUserName) ) {
                    item.session.getAsyncRemote().sendText(message);
                    break;
                }
            }
        }

        public void sendMessageAll(String message,String FromUserName) throws IOException {
            for (WebSocket item : clients.values()) {
                item.session.getAsyncRemote().sendText(message);
            }
        }

        public static synchronized int getOnlineCount() {
            return onlineNumber;
        }

    }
