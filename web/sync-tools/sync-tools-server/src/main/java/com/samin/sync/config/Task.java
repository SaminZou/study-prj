package com.samin.sync.config;

import com.samin.sync.controller.WebSocketController;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@EnableScheduling
@Configuration
public class Task {

    private final WebSocketController webSocket;

    public Task(WebSocketController webSocketController) {
        this.webSocket = webSocketController;
    }

    // 每分钟执行一次
    @Scheduled(fixedRate = 60000)
    public void printOnlineStats() {
        int onlineCount = webSocket.getOnlineCount();
        Set<String> onlineIps = webSocket.getOnlineIps();
        log.info("当前在线人数: {}，在线 IP: {}", onlineCount, onlineIps);
    }
}