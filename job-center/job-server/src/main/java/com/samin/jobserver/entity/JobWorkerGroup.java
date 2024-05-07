package com.samin.jobserver.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "job_worker_group")
public class JobWorkerGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Comment("执行器分组名")
    private String name;

    @Comment("执行器编码")
    private String appCode;

    @Comment("是否停用（0：否，1：是）")
    private Integer isEnable;

    @Comment("是否删除（0：否，1：是）")
    private Integer isDelete;

    @Comment("创建时间")
    private LocalDateTime createTime;

    @Comment("更新时间")
    private LocalDateTime updateTime;
}
