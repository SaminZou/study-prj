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
    @Value("${custom.project-name}")
    private String projectName;

    public void dingtalk(AlertReq req) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String now = LocalDateTime.now().format(dtf);

        for (Alerts alerts : req.getAlerts()) {
            StringBuilder sb = new StringBuilder();
            sb.append("- 所属项目：").append(projectName).append("\n");
            sb.append("- 告警时间：").append(now).append("\n");

            if (StringUtils.isNotBlank(alerts.getLabels().getApplication())) {
                sb.append("- 告警应用：").append(alerts.getLabels().getApplication()).append("\n");
            }

            if (StringUtils.isNotBlank(alerts.getLabels().getInstance())) {
                sb.append("- 告警实例：").append(alerts.getLabels().getInstance()).append("\n");
            }

            if (StringUtils.isNotBlank(alerts.getLabels().getUri())) {
                sb.append("- 请求名：").append(alerts.getLabels().getUri()).append("\n");
            }

            if (StringUtils.isNotBlank(alerts.getLabels().getMethod())) {
                sb.append("- 请求方法：").append(alerts.getLabels().getMethod()).append("\n");
            }

            if (StringUtils.isNotBlank(alerts.getAnnotations().getSummary())) {
                sb.append("- 告警条目：").append(alerts.getAnnotations().getSummary()).append("\n");
            }

            if (StringUtils.isNotBlank(alerts.getAnnotations().getDescription())) {
                sb.append("- 告警描述：").append(alerts.getAnnotations().getDescription()).append("\n");
            }

            log.info("\n{}", sb);

            // 构建请求体
            Markdown markdown = new Markdown();
            markdown.setTitle("监控告警");
            markdown.setText(sb.toString());
            At at = new At();
            at.setIsAtAll("false");

            DingtalkReq dingtalkReq = new DingtalkReq();
            dingtalkReq.setMsgtype("markdown");
            dingtalkReq.setMarkdown(markdown);
            dingtalkReq.setAt(at);

            ResponseEntity<DingtalkResp> dingtalkResp = restTemplate.postForEntity(dingtalkUrl, dingtalkReq,
                    DingtalkResp.class);
            if (dingtalkResp.getStatusCode().equals(HttpStatus.OK) && Objects.nonNull(dingtalkResp.getBody())
                    && dingtalkResp.getBody().getErrcode() == 0) {
                log.info("告警信息钉钉转发成功 {}", now);
            }
        }
    }
}
