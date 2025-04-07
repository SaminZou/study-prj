package com.samin.sync.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.stereotype.Component;

@Component
public class WebSocketUpgradeFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        String upgradeHeader = httpRequest.getHeader("Upgrade");

        // 检查是否是 WebSocket 握手请求
        if ("websocket".equalsIgnoreCase(upgradeHeader)) {
            filterChain.doFilter(servletRequest, servletResponse); // 如果是 WebSocket 握手请求，直接放行
        } else {
            // 如果是普通 HTTP 请求，返回 400 或引导客户端改用 WebSocket
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            httpResponse.getWriter()
                        .write("This endpoint only supports WebSocket connections.");
        }
    }
}