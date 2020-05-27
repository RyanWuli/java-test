package com.example.socketio.client;

import io.socket.client.IO;
import io.socket.client.Socket;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;
import java.util.Date;

/**
 * @Author: Ryan
 * @Date: 2020/5/9 13:53
 * @Version: 1.0
 * @Description:
 */
@Slf4j
public class SocketIOClientLaunch {

    public static void main(String[] args) {

        String url = "http://127.0.0.1:8888";
        // 服务端 socketio 通信地址
        IO.Options options = new IO.Options();
        options.transports = new String[] {"websocket"};
        options.reconnectionAttempts = 2;
        // 失败重连时间间隔
        options.reconnectionDelay = 1000;
        // 连接超时时间
        options.timeout = 5000;
        // userId 唯一标识，传给服务器
        try {
            final Socket socket = IO.socket(url + "?userId=1", options);
            socket.on(Socket.EVENT_CONNECT, args1 -> socket.send("hello"));

            // 自定义 connection 事件，接收服务器连接成功消息
            socket.on("connection", objects -> log.info("服务端：" + objects[0].toString()));

            // 自定义 push_data_event，接收服务端消息
            socket.on("push_data_event", objects -> log.info("服务端：" + objects[0].toString()));

            // 自定义 myBroadcast 事件，接受服务端的广播消息
            socket.on("myBroadcast", objects -> log.info("服务端：" + objects[0].toString()));

            socket.connect();

            while (true) {
                Thread.sleep(50000);
                socket.emit("push_data_event", "发送数据" + new Date());
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
