package com.samin.jobadmin.bean;

import com.samin.jobadmin.entity.Job;
import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Data
public class JobVo {

    public static JobVo getInstance(Job job) {
        JobVo ins = new JobVo();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        ins.setId(job.getId());
        ins.setName(job.getName());
        ins.setDescription(job.getDescription());
        ins.setAppCode(job.getAppCode());
        ins.setActionCode(job.getActionCode());
        ins.setCron(job.getCron());
        ins.setParamJson(job.getParamJson());
        ins.setIsDelete(job.getIsDelete());
        ins.setIsEnable(job.getIsEnable());
        ins.setProcessTime(Objects.nonNull(job.getProcessTime()) ? dtf.format(job.getProcessTime()) : "");
        ins.setCreateTime(Objects.nonNull(job.getCreateTime()) ? dtf.format(job.getCreateTime()) : "");
        ins.setUpdateTime(Objects.nonNull(job.getUpdateTime()) ? dtf.format(job.getUpdateTime()) : "");

        return ins;
    }

    private Integer id;

    private String name;

    private String description;

    private String appCode;

    private String actionCode;

    private String cron;

    private String paramJson;

    private Integer isDelete;

    private Integer isEnable;

    private String processTime;

    private String createTime;

    private String updateTime;
}
