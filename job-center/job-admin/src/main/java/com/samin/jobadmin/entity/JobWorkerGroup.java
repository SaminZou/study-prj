package com.samin.jobadmin.entity;

import lombok.Data;
import org.hibernate.annotations.Comment;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class JobWorkerGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Comment("执行器分组名")
    private String name;

    @Comment("执行器编码")
    private String appCode;

    @Comment("创建时间")
    private String createTime;

    @Comment("更新时间")
    private String updateTime;
}
