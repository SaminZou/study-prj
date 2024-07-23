package com.samin.jobadmin.entity;

import lombok.Data;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "job_worker")
public class JobWorker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Comment("分组ID")
    private Integer groupId;

    @Comment("地址，唯一标识")
    private String address;

    @Comment("状态（0：离线，1：在线）")
    private Integer status;

    @Comment("注册时间")
    private LocalDateTime createTime;

    @Comment("更新时间")
    private LocalDateTime updateTime;
}
