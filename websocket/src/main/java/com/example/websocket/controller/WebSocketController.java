package com.example.websocket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: Ryan
 * @Date: 2020/5/7 11:48
 * @Version: 1.0
 * @Description:
 */
@Controller
public class WebSocketController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/websocket/{name}")
    public String webSocket(@PathVariable String name, Model model){
        try{
            logger.info("跳转到websocket的页面上");
            model.addAttribute("username",name);
            return "websocket";
        }
        catch (Exception e){
            logger.info("跳转到websocket的页面上发生异常，异常信息是："+e.getMessage());
            return "error";
        }
    }
}
