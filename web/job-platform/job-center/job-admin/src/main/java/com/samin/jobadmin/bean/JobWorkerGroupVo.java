package com.samin.jobadmin.bean;

import com.samin.jobadmin.entity.JobWorkerGroup;
import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Data
public class JobWorkerGroupVo {

    public static JobWorkerGroupVo getInstance(JobWorkerGroup jobWorkerGroup) {
        JobWorkerGroupVo ins = new JobWorkerGroupVo();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        ins.setId(jobWorkerGroup.getId());
        ins.setName(jobWorkerGroup.getName());
        ins.setAppCode(jobWorkerGroup.getAppCode());
        ins.setCreateTime(Objects.nonNull(jobWorkerGroup.getCreateTime()) ? dtf.format(jobWorkerGroup.getCreateTime()) : "");
        ins.setUpdateTime(Objects.nonNull(jobWorkerGroup.getUpdateTime()) ? dtf.format(jobWorkerGroup.getUpdateTime()) : "");

        return ins;
    }

    private Integer id;

    private String name;

    private String appCode;

    private String createTime;

    private String updateTime;
}
