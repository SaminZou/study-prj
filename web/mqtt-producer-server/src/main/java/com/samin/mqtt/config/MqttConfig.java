package com.samin.mqtt.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.event.MqttConnectionFailedEvent;
import org.springframework.util.StringUtils;

@Slf4j
@Configuration
@EnableIntegration
@IntegrationComponentScan
@RequiredArgsConstructor
public class MqttConfig {

    private final MqttProperties mqttProperties;

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();

        if (StringUtils.hasText(mqttProperties.getUsername())) {
            options.setUserName(mqttProperties.getUsername());
            options.setPassword(mqttProperties.getPassword() == null ? new char[]{} : mqttProperties.getPassword().toCharArray());
        }
        options.setServerURIs(new String[]{mqttProperties.getUrl()});
        options.setConnectionTimeout(mqttProperties.getConnectionTimeout());
        options.setKeepAliveInterval(mqttProperties.getKeepAlive());
        options.setCleanSession(mqttProperties.isCleanSession());
        options.setAutomaticReconnect(true);
        options.setMaxReconnectDelay(5000);
        options.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1);
        factory.setConnectionOptions(options);

        return factory;
    }

    @EventListener
    public void handleConnectionFailed(MqttConnectionFailedEvent event) {
        log.error("【MQTT 连接失败】{}", event.getCause().getMessage());
    }
}