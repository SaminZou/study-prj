package com.samin.auth.vo.resp;

import com.samin.auth.entity.SystemLog;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import lombok.Data;

@Data
public class LogResp {

    private Integer id;
    private Integer userId;
    private String remark;
    private String clientIpAddress;
    private String requestTime;
    private String requestPath;
    private String requestMethod;
    private Integer responseStatusCode;
    private Long executionTime;
    private String deviceInformation;
    private String createTime;

    public static LogResp getInstance(SystemLog log) {
        // TODO 换成统一处理，保持每个返回数据的时间格式一致
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LogResp ins = new LogResp();

        ins.setId(log.getId());
        ins.setUserId(log.getUserId());
        ins.setRemark(log.getRemark());
        ins.setClientIpAddress(log.getClientIpAddress());
        ins.setRequestTime(Objects.nonNull(log.getRequestTime()) ? dtf.format(log.getRequestTime()) : "");
        ins.setRequestPath(log.getRequestPath());
        ins.setRequestMethod(log.getRequestMethod());
        ins.setResponseStatusCode(log.getResponseStatusCode());
        ins.setExecutionTime(log.getExecutionTime());
        ins.setDeviceInformation(log.getDeviceInformation());
        ins.setCreateTime(Objects.nonNull(log.getCreateTime()) ? dtf.format(log.getCreateTime()) : "");

        return ins;
    }
}
