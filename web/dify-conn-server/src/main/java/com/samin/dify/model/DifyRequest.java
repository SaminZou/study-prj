package com.samin.dify.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Dify API 请求对象
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DifyRequest {

    /**
     * 输入参数
     */
    private Map<String, Object> inputs;

    /**
     * 用户查询内容
     */
    private String query;

    /**
     * 响应模式：blocking 或 streaming
     */
    private String responseMode;

    /**
     * 用户标识
     */
    private String user;
}



