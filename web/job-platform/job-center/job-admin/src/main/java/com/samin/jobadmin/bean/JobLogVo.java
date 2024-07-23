package com.samin.jobadmin.bean;

import com.samin.jobadmin.entity.JobLog;
import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Data
public class JobLogVo {

    public static JobLogVo getInstance(JobLog jobLog) {
        JobLogVo ins = new JobLogVo();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        ins.setId(jobLog.getId());
        ins.setJobId(jobLog.getJobId());
        ins.setResult(jobLog.getResult());
        ins.setErrorMsg(jobLog.getErrorMsg());
        ins.setStartTime(Objects.nonNull(jobLog.getStartTime()) ? dtf.format(jobLog.getStartTime()) : "");
        ins.setEndTime(Objects.nonNull(jobLog.getEndTime()) ? dtf.format(jobLog.getEndTime()) : "");
        ins.setCreateTime(Objects.nonNull(jobLog.getCreateTime()) ? dtf.format(jobLog.getCreateTime()) : "");
        ins.setUpdateTime(Objects.nonNull(jobLog.getUpdateTime()) ? dtf.format(jobLog.getUpdateTime()) : "");

        return ins;
    }

    private Integer id;
    private Integer jobId;
    private Boolean result;
    private String errorMsg;
    private String startTime;
    private String endTime;
    private String createTime;
    private String updateTime;
}
