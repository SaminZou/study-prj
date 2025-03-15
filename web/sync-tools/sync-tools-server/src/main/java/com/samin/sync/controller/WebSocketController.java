package com.samin.sync.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.samin.sync.entity.SyncVo;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketController implements WebSocketHandler {

    // 维护一个连接池，存储所有的 WebSocketSession
    private static final Map<String, WebSocketSession> SESSION_POOL = new ConcurrentHashMap<>();

    private final ObjectMapper objectMapper;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String remoteIp = session.getRemoteAddress()
                                 .getAddress()
                                 .getHostAddress();
        SESSION_POOL.put(session.getId(), session); // 添加到连接池
        log.info("客户端连接成功: sessionId={}，IP={}", session.getId(), remoteIp);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String payload = message.getPayload()
                                .toString(); // 获取客户端发送的消息
        log.info("收到客户端消息: sessionId={}，消息={}", session.getId(), payload);

        SyncVo syncVo = new SyncVo();
        syncVo.setPayload(payload);
        syncVo.setOnlineCount(getOnlineCount());

        // 将消息广播给所有在线用户
        broadcastMessage(objectMapper.writeValueAsString(syncVo));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.error("WebSocket 传输错误: sessionId={}, 错误={}", session.getId(), exception.getMessage());
        SESSION_POOL.remove(session.getId());
        session.close();
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        log.info("客户端断开连接: sessionId={}，状态={}", session.getId(), closeStatus);
        SESSION_POOL.remove(session.getId());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    // 广播消息
    public void broadcastMessage(String message) {
        SESSION_POOL.values()
                    .forEach(session -> {
                        try {
                            if (session.isOpen()) {
                                session.sendMessage(new TextMessage(message));
                            }
                        } catch (Exception e) {
                            log.error("消息广播失败: sessionId={}, 错误={}", session.getId(), e.getMessage());
                        }
                    });
    }

    // 获取在线人数
    public int getOnlineCount() {
        return SESSION_POOL.size();
    }

    // 获取在线 IP 列表
    public Set<String> getOnlineIps() {
        return SESSION_POOL.values()
                           .stream()
                           .map(session -> session.getRemoteAddress()
                                                  .getAddress()
                                                  .getHostAddress())
                           .collect(Collectors.toSet());
    }

    // 清理失效的 session
    public void cleanExpiredSessions() {
        SESSION_POOL.values()
                    .removeIf(session -> !session.isOpen());
    }
}