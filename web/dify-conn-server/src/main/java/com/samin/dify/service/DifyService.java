package com.samin.dify.service;

import com.samin.dify.config.DifyApiConfig;
import com.samin.dify.model.DifyRequest;
import com.samin.dify.model.DifyResp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * Dify 服务类
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class DifyService {

    private final RestTemplate restTemplate;
    private final DifyApiConfig difyConfig;

    /**
     * 发送消息到 Dify API
     *
     * @param userMessage 用户消息
     * @return Dify 返回的答案
     * @throws RestClientException 当 API 调用失败时抛出
     */
    public String sendMessageToDify(String userMessage) {
        if (userMessage == null || userMessage.trim().isEmpty()) {
            throw new IllegalArgumentException("用户消息不能为空");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(difyConfig.getApiKey());

        DifyRequest requestBody = DifyRequest.builder()
                .inputs(Collections.emptyMap())
                .query(userMessage)
                .responseMode("blocking")
                .user(difyConfig.getUserId())
                .build();

        HttpEntity<DifyRequest> request = new HttpEntity<>(requestBody, headers);

        log.debug("调用 Dify API: {}", difyConfig.getApiUrl());
        try {
            ResponseEntity<DifyResp> response = restTemplate.postForEntity(
                    difyConfig.getApiUrl(), 
                    request, 
                    DifyResp.class
            );

            if (response.getBody() == null) {
                log.warn("Dify API 返回空响应");
                return "No response from Dify.";
            }

            String answer = response.getBody().getAnswer();
            return answer != null && !answer.trim().isEmpty() 
                    ? answer 
                    : "No response from Dify.";
        } catch (RestClientException e) {
            log.error("调用 Dify API 失败: {}", e.getMessage(), e);
            throw new RuntimeException("调用 Dify API 失败: " + e.getMessage(), e);
        }
    }
}
