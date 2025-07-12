package com.samin.mqtt.config;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.event.MqttConnectionFailedEvent;

@Slf4j
@Configuration
@EnableIntegration
@IntegrationComponentScan
public class MqttConfig {

    @Value("${mqtt.username}")
    private String username;
    @Value("${mqtt.password}")
    private String password;
    @Value("${mqtt.url}")
    private String hostUrl;

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();

        // 设置连接的用户名
        if (!username.trim()
                .isEmpty()) {
            options.setUserName(username);
            // 设置连接的密码
            options.setPassword(password.toCharArray());
        }
        // 设置代理端的 URL 地址，可以是多个
        options.setServerURIs(new String[]{hostUrl});
        // 设置超时时间，单位为秒
        options.setConnectionTimeout(10);
        // 设置会话心跳时间，单位为秒，服务器会每隔 1.5*20 秒的时间向客户端发送心跳判断客户端是否在线，但这个方法并没有重连的机制
        options.setKeepAliveInterval(20);
        options.setCleanSession(true);
        options.setAutomaticReconnect(true);
        options.setMaxReconnectDelay(5000);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        factory.setConnectionOptions(options);

        return factory;
    }

    @EventListener
    public void handleConnectionFailed(MqttConnectionFailedEvent event) {
        log.error("【MQTT 连接失败！】: {}", event.getCause().getMessage());
    }
}