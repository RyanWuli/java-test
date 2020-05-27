package com.example.socketio.service.impl;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.example.socketio.service.ISocketIOService;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Ryan
 * @Date: 2020/5/9 11:27
 * @Version: 1.0
 * @Description:
 */
@Service("socketIOService")
public class SocketIOServiceImpl implements ISocketIOService {

    /**
     * 存放已连接的客户端
     */
    private static Map<String, SocketIOClient> clientMap = new ConcurrentHashMap<>();
    /**
     * 自定义事件，用于服务端与客户端的通信
     */
    private static final String PUSH_DATA_EVENT = "push_data_event";

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SocketIOServer socketIOServer;

    /**
     * Spring IOC 容器启动之后，在加载 SocketIOServiceImpl 之后启动
     */
    @PostConstruct
    private void autoStartup() {
        start();
    }

    /**
     * 在加载 SocketIOServiceImpl 销毁之前关闭，避免重复启动服务端
     */
    @PreDestroy
    private void autoStop() {
        stop();
    }

    @Override
    public void start() {
        // 监听客户端连接
        socketIOServer.addConnectListener(client -> {
            logger.info("**********客户端：" + getIpByClient(client) + "已连接**********");
            client.sendEvent("connection", "你成功的连接上了服务器哦。。。");
            String userId = getParamByClient(client);
            if (userId != null) {
                clientMap.put(userId, client);
            }
        });

        // 监听客户断开连接
        socketIOServer.addDisconnectListener(client -> {
            String clientIp = getIpByClient(client);
            logger.info("**********客户端：" + clientIp + "已断开连接**********");
            String userId = getParamByClient(client);
            if (userId != null) {
                clientMap.remove(userId);
                client.disconnect();
            }
        });

        // 自定义 client_info_event,监听客户端消息
        socketIOServer.addEventListener(PUSH_DATA_EVENT, String.class, (client, data, ackSender) -> {
            // 客户端推送`client_info_event`事件时，onData接受数据，这里是string类型的json数据，还可以为Byte[],object其他类型
            String clientIp = getIpByClient(client);
            logger.info("**********客户端数据：" + data);
        });

        // 启动服务
        socketIOServer.start();

        // broadcast: 默认是向所有的socket连接进行广播，但是不包括发送者自身，如果自己也打算接收消息的话，需要给自己单独发送。
        new Thread(() -> {
            int i = 0;
            while (true) {
                try {
                    // 每隔50秒发送一次广播消息
                    Thread.sleep(50000);
                    socketIOServer.getBroadcastOperations().sendEvent("myBroadcast", "广播消息" + new Date());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void stop() {
        if (socketIOServer != null) {
            socketIOServer.stop();
            socketIOServer = null;
        }
    }

    @Override
    public void pushMessageToUser(String userId, String msgContent) {
        SocketIOClient client = clientMap.get(userId);
        System.out.println(client);
        if (client != null) {
            client.sendEvent(PUSH_DATA_EVENT, msgContent);
        }
    }

    /**
     * 从客户端对象拿到客户端的ip
     * @param client 客户端对象
     * @return ip
     */
    private String getIpByClient(SocketIOClient client) {
        String sa = client.getRemoteAddress().toString();
        return sa.substring(1, sa.indexOf(":"));
    }

    private String getParamByClient(SocketIOClient client) {
        // 获取客户端 url 参数（这里的 userId 是唯一标识）
        Map<String, List<String>> params = client.getHandshakeData().getUrlParams();
        List<String> userIdList = params.get("userId");
        if (!CollectionUtils.isEmpty(userIdList)) {
            return userIdList.get(0);
        }
        return null;
    }
}
