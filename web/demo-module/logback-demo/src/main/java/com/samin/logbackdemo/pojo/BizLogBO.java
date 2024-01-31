package com.samin.logbackdemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

/**
 * 日志记录类
 *
 * @author samin
 * @date 2021-10-12
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BizLogBO {

    private Long id;

    /**
     * 事件名称，方法名
     */
    private String eventName;

    /**
     * C端用户id
     */
    private String userId;

    /**
     * 结果消息
     */
    private String resultMsg;

    /**
     * 接口响应时间
     */
    private Long costTime;

    /**
     * 接口请求入参
     */
    private String request;

    /**
     * 接口返回值
     */
    private String response;

    /**
     * 其他业务参数
     */
    private String others;

    /**
     * 创建时间
     */
    @Builder.Default
    private OffsetDateTime createTime = OffsetDateTime.now();
}
