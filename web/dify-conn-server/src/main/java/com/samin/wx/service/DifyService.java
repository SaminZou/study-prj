package com.samin.wx.service;

import com.samin.wx.config.DifyApiConfig;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class DifyService {

    private final RestTemplate restTemplate;
    private final DifyApiConfig difyConfig;

    public String sendMessageToDify(String userMessage) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(difyConfig.getApiKey());

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("inputs", Collections.emptyMap());
        requestBody.put("query", userMessage);
        requestBody.put("response_mode", "blocking");
        requestBody.put("user", "server-001");

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(difyConfig.getApiUrl(), request, Map.class);

        String data = (String) response.getBody()
                                       .get("answer");
        return Objects.nonNull(data) ? data : "No response from Dify.";
    }
}
