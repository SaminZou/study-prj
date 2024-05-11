package com.samin.jobserver.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.NotFound;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 定时任务名称
     */
    @Comment("定时任务名称")
    private String name;

    /**
     * 描述
     */
    @Comment("描述")
    private String description;

    /**
     * 执行器编码
     *
     * TODO 修改为 Job Worker Group 执行，必填项
     */
    @NotFound
    @Comment("执行器编码")
    private String appCode;

    /**
     * 执行方法编码
     */
    @Comment("执行方法编码")
    private String actionCode;

    /**
     * 周期表达式
     */
    @Comment("周期表达式")
    private String cron;

    /**
     * 参数，json 字符串
     */
    @Comment("参数，json 字符串")
    private String paramJson;

    /**
     * 是否删除（0：否，1：是）
     */
    @Comment("是否删除（0：否，1：是）")
    private Integer isDelete;

    /**
     * 是否停用（0：否，1：是）
     */
    @Comment("是否停用（0：否，1：是）")
    private Integer isEnable;

    /**
     * 执行时间
     */
    @Comment("执行时间")
    private LocalDateTime processTime;

    /**
     * 创建时间
     */
    @Comment("创建时间")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Comment("更新时间")
    private LocalDateTime updateTime;
}
