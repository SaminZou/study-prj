package com.samin.auth.vo.resp;

import com.samin.auth.entity.SystemLog;
import com.samin.auth.util.DateUtil;
import lombok.Data;

@Data
public class LogResp {

    public static LogResp getInstance(SystemLog log) {
        LogResp ins = new LogResp();

        ins.setId(log.getId());
        ins.setUserId(log.getUserId());
        ins.setRemark(log.getRemark());
        ins.setClientIpAddress(log.getClientIpAddress());
        ins.setRequestTime(DateUtil.getDisplayTime(log.getRequestTime()));
        ins.setRequestPath(log.getRequestPath());
        ins.setRequestMethod(log.getRequestMethod());
        ins.setResponseStatusCode(log.getResponseStatusCode());
        ins.setExecutionTime(log.getExecutionTime());
        ins.setDeviceInformation(log.getDeviceInformation());
        ins.setCreateTime(DateUtil.getDisplayTime(log.getCreateTime()));

        return ins;
    }

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
}
