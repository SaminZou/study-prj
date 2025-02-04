package com.samin.auth.config;

import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Data;

public class RequestThreadLocal {

    private static final ThreadLocal<Request> requestThreadLocal = new ThreadLocal<>();

    public static void clear() {
        requestThreadLocal.remove();
    }

    public static Request getRequest() {
        if (Objects.isNull(requestThreadLocal.get())) {
            Request request = new Request();
            requestThreadLocal.set(request);
            return request;
        } else {
            return requestThreadLocal.get();
        }
    }

    @Data
    public static class Request {

        private LocalDateTime requestTime;
        private Long requestStartTime;
        private String body;
        private String response;
    }
}