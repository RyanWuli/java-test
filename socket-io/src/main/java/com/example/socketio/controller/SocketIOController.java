package com.example.socketio.controller;

import com.example.socketio.service.ISocketIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Ryan
 * @Date: 2020/5/9 15:17
 * @Version: 1.0
 * @Description:
 */
@RestController
public class SocketIOController {

    @Autowired
    ISocketIOService service;

    @RequestMapping("/socket")
    public String pushMessageToUser(@RequestParam String userId, @RequestParam String msgContent) {
        service.pushMessageToUser(userId, msgContent);
        return "消息发送成功";
    }
}
