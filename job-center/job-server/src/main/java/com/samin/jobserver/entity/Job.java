package com.samin.jobserver.entity;

import lombok.Data;
import org.hibernate.annotations.Comment;

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
