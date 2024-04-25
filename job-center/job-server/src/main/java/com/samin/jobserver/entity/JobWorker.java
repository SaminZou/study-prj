package com.samin.jobserver.entity;

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

    @Comment("地址")
    private String address;

    @Comment("注册时间")
    private LocalDateTime createTime;
}
