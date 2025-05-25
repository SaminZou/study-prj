package com.samin.usecase.apilogs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.ServletRequestHandledEvent;

@Component
@Slf4j
public class RequestTimingEventListener implements ApplicationListener<ServletRequestHandledEvent> {

    @Override
    public void onApplicationEvent(ServletRequestHandledEvent event) {
        // 获取请求失败的原因 event.getFailureCause()
        Throwable failureCause = event.getFailureCause();
        String failureMessage = (failureCause == null) ? "" : failureCause.getMessage();

        // 获取请求相关的信息
        String clientAddress = event.getClientAddress();
        String requestUrl = event.getRequestUrl();
        String method = event.getMethod();

        // event.getProcessingTimeMillis() 获取处理耗时
        long processingTimeMillis = event.getProcessingTimeMillis();

        // 日志输出请求处理信息
        if (failureCause == null) {
            log.info("客户端地址: {}，请求路径: {}，请求方法: {}，处理耗时: {} 毫秒", clientAddress, requestUrl, method,
                     processingTimeMillis);
        } else {
            log.error("客户端地址: {}，请求路径: {}，请求方法: {}，处理耗时: {} 毫秒，错误信息: {}", clientAddress, requestUrl,
                      method, processingTimeMillis, failureMessage);
        }
    }
}