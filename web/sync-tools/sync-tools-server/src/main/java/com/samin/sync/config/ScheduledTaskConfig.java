package com.samin.sync.config;

import com.samin.sync.controller.WebSocketController;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@EnableScheduling
@Configuration
@RequiredArgsConstructor
public class ScheduledTaskConfig {

    private final WebSocketController webSocket;

    // 每分钟执行一次
    @Scheduled(fixedRate = 60000)
    public void printOnlineStats() {
        int onlineCount = webSocket.getOnlineCount();
        Set<String> onlineIps = webSocket.getOnlineIps();
        log.info("当前在线人数: {}，在线 IP: {}", onlineCount, onlineIps);
    }

    // 每 10 秒执行一次，清理失效的 session
    @Scheduled(fixedRate = 10000)
    public void cleanExpiredSessions() {
        webSocket.cleanExpiredSessions();
    }
}