package com.samin.mqtt.config;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration
public class MqttOutboundPoolConfig {

    @Value("${mqtt.client-id-prefix}")
    private String clientIdPrefix;
    @Autowired
    private MqttPahoClientFactory mqttClientFactory;

    /**
     * 建议与异步线程池核心数相同或略大
     */
    private static final int MQTT_CLIENT_POOL_SIZE = 12;

    private final List<MqttPahoMessageHandler> handlerPool = new CopyOnWriteArrayList<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    @PostConstruct
    public void init() {
        log.info("初始化 MQTT 客户端池，大小: {}", MQTT_CLIENT_POOL_SIZE);
        for (int i = 0; i < MQTT_CLIENT_POOL_SIZE; i++) {
            handlerPool.add(createNewHandler(i));
        }
        log.info("MQTT 客户端池初始化完成，共 {} 个连接", handlerPool.size());
    }

    private MqttPahoMessageHandler createNewHandler(int index) {
        String clientId = clientIdPrefix + "-pub-" + index;
        MqttPahoMessageHandler handler = new MqttPahoMessageHandler(clientId, mqttClientFactory);
        handler.setAsync(true);
        handler.setDefaultQos(0);
        handler.setDefaultTopic("default");
        handler.setDefaultRetained(false);
        handler.setConverter(new DefaultPahoMessageConverter());
        handler.start();
        log.info("MQTT 客户端 [{}] 已启动连接", clientId);
        return handler;
    }

    /**
     * 获取一个 handler（轮询方式）
     */
    public MqttPahoMessageHandler getHandler() {
        int index = Math.abs(counter.getAndIncrement() % handlerPool.size());
        return handlerPool.get(index);
    }

    @Scheduled(initialDelay = 60000, fixedDelay = 60000)
    public void checkMqttClients() {
        log.debug("执行 MQTT 客户端池健康检查...");
        for (int i = 0; i < handlerPool.size(); i++) {
            MqttPahoMessageHandler handler = handlerPool.get(i);
            if (!handler.isRunning()) {
                log.info("检测到 MQTT 客户端断开，准备重建: {}", handler.getClientId());
                handler.stop();
                handlerPool.set(i, createNewHandler(i));
            }
        }
    }
}
