package com.samin.mqtt.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@EnableScheduling
@Configuration
@RequiredArgsConstructor
public class TaskConfig {

    private final MessageProducerSupport adapter;

    /**
     * 每分钟检测 MQTT 是否正常
     */
    @Scheduled(fixedRate = 60000)
    public void checkMqttHealth() {
        if (adapter.isRunning()) {
            log.debug("[MQTT] 连接正常");
            return;
        }
        log.warn("[MQTT] 连接已停止，尝试重启");
        try {
            adapter.stop();
            adapter.start();
            log.info("[MQTT] 重启完成，当前状态：{}", adapter.isRunning());
        } catch (Exception e) {
            log.error("[MQTT] 重启失败", e);
        }
    }
}
