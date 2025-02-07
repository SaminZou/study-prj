package com.samin.sync.config;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

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
            httpResponse.getWriter().write("This endpoint only supports WebSocket connections.");
        }
    }
}