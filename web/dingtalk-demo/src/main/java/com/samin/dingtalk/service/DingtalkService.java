package com.samin.dingtalk.service;

import com.samin.dingtalk.pojo.req.AlertReq;
import com.samin.dingtalk.pojo.req.At;
import com.samin.dingtalk.pojo.req.DingtalkReq;
import com.samin.dingtalk.pojo.req.Markdown;
import com.samin.dingtalk.pojo.resp.DingtalkResp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class DingtalkService {

    private final RestTemplate restTemplate;

    @Value("${custom.dingtalk-url}")
    private String dingtalkUrl;

    public void dingtalk(AlertReq req) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String now = LocalDateTime.now().format(dtf);
        String text = String.format(
                "- 告警应用：%s\n" + "- 告警实例：%s\n" + "- 告警时间：%s\n" + "- 告警详情：%s\n" + "- 请求名：%s\n"
                        + "- 请求方法：%s", req.getCommonLabels().getApplication(), req.getCommonLabels().getInstance(), now,
                req.getCommonAnnotations().getSummary(), req.getCommonLabels().getUri(), req.getCommonLabels().getMethod());

        // 构建请求体
        Markdown markdown = new Markdown();
        markdown.setTitle("监控告警");
        markdown.setText(text);
        At at = new At();
        at.setIsAtAll("false");

        DingtalkReq dingtalkReq = new DingtalkReq();
        dingtalkReq.setMsgtype("markdown");
        dingtalkReq.setMarkdown(markdown);
        dingtalkReq.setAt(at);

        ResponseEntity<DingtalkResp> dingtalkResp = restTemplate.postForEntity(dingtalkUrl, dingtalkReq, DingtalkResp.class);
        if (dingtalkResp.getStatusCode().equals(HttpStatus.OK) && dingtalkResp.getBody().getErrcode() == 0) {
            log.info("告警信息钉钉转发成功 {}", now);
        }
    }
}
