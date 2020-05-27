package com.example.socketio.config;


import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Ryan
 * @Date: 2020/5/9 11:05
 * @Version: 1.0
 * @Description:
 */
@Configuration
public class SocketIOConfig {

    @Value("${socketio.host}")
    private String host;
    @Value("${socketio.port}")
    private Integer port;
    @Value("${socketio.bossCount}")
    private int bossCount;
    @Value("${socketio.workCount}")
    private int workCount;
    @Value("${socketio.allowCustomRequests}")
    private boolean allowCustomRequests;
    @Value("${socketio.upgradeTimeout}")
    private int upgradeTimeout;
    @Value("${socketio.pingTimeout}")
    private int pingTimeout;
    @Value("${socketio.pingInterval}")
    private int pingInterval;

    @Bean
    public SocketIOServer socketIOServer() {
        SocketConfig socketConfig = new SocketConfig();
        socketConfig.setTcpNoDelay(true); // 关闭nagle算法，默认false
        socketConfig.setSoLinger(0);
        com.corundumstudio.socketio.Configuration configuration = new com.corundumstudio.socketio.Configuration();
        configuration.setSocketConfig(socketConfig);
        configuration.setHostname(host);
        configuration.setPort(port);
        configuration.setBossThreads(bossCount);
        configuration.setWorkerThreads(workCount);
        configuration.setAllowCustomRequests(allowCustomRequests);
        configuration.setUpgradeTimeout(upgradeTimeout);
        configuration.setPingTimeout(pingTimeout);
        configuration.setPingInterval(pingInterval);
        return new SocketIOServer(configuration);
    }

}
