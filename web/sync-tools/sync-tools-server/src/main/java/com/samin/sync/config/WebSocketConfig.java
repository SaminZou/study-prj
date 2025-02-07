package com.samin.sync.config;

import com.samin.sync.controller.WebSocketController;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final WebSocketController webSocketController;

    public WebSocketConfig(WebSocketController webSocketController) {
        this.webSocketController = webSocketController;
    }

    @Bean
    public FilterRegistrationBean<WebSocketUpgradeFilter> webSocketFilter() {
        FilterRegistrationBean<WebSocketUpgradeFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new WebSocketUpgradeFilter());
        // 只拦截 WebSocket 端点
        registrationBean.addUrlPatterns("/ws");
        registrationBean.setName("WebSocketUpgradeFilter");
        return registrationBean;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketController, "/ws")
                .addInterceptors(new HttpSessionHandshakeInterceptor())
                .setHandshakeHandler(new DefaultHandshakeHandler()) // 自动处理握手
                .setAllowedOriginPatterns("*"); // 允许跨域
    }
}