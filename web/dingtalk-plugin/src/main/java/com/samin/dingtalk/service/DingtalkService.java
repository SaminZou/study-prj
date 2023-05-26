package com.samin.dingtalk.service;

import com.samin.dingtalk.pojo.req.*;
import com.samin.dingtalk.pojo.resp.DingtalkResp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class DingtalkService {

    private final RestTemplate restTemplate;

    @Value("${custom.dingtalk-url}")
    private String dingtalkUrl;

    private final static String ALARM_MSG_TEMPLATE1 = "- 告警应用：%s\n- 告警实例：%s\n- 请求名：%s\n- 请求方法：%s\n- 告警时间：%s\n- 告警标题：%s";
    private final static String ALARM_MSG_TEMPLATE2 = "- 告警应用：%s\n- 告警实例：%s\n- 请求名：%s\n- 请求方法：%s\n- 告警时间：%s\n- 告警标题：%s\n- 告警描述：%s";

    public void dingtalk(AlertReq req) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String now = LocalDateTime.now().format(dtf);

        for (Alerts alerts : req.getAlerts()) {
            String text;
            if (StringUtils.isNotBlank(alerts.getAnnotations().getDescription())) {
                text = String.format(ALARM_MSG_TEMPLATE2, alerts.getLabels().getApplication(), alerts.getLabels().getInstance(),
                        alerts.getLabels().getUri(), alerts.getLabels().getMethod(), now, alerts.getAnnotations().getSummary()
                        , alerts.getAnnotations().getDescription());
            } else {
                text = String.format(ALARM_MSG_TEMPLATE1, alerts.getLabels().getApplication(), alerts.getLabels().getInstance(),
                        alerts.getLabels().getUri(), alerts.getLabels().getMethod(), now, alerts.getAnnotations().getSummary());
            }

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

//            ResponseEntity<DingtalkResp> dingtalkResp = restTemplate.postForEntity(dingtalkUrl, dingtalkReq,
//                    DingtalkResp.class);
//            if (dingtalkResp.getStatusCode().equals(HttpStatus.OK) && Objects.nonNull(dingtalkResp.getBody())
//                    && dingtalkResp.getBody().getErrcode() == 0) {
//                log.info("告警信息钉钉转发成功 {}", now);
//            }
        }
    }
}
