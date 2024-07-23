package com.samin.jobadmin.bean;

import com.samin.jobadmin.entity.JobWorker;
import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Data
public class JobWorkerVo {

    public static JobWorkerVo getInstance(JobWorker jobWorker) {
        JobWorkerVo ins = new JobWorkerVo();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        ins.setId(jobWorker.getId());
        ins.setGroupId(jobWorker.getGroupId());
        ins.setAddress(jobWorker.getAddress());
        ins.setCreateTime(Objects.nonNull(jobWorker.getCreateTime()) ? dtf.format(jobWorker.getCreateTime()) : "");

        return ins;
    }

    private Integer id;

    private Integer groupId;

    private String address;

    private String createTime;
}
