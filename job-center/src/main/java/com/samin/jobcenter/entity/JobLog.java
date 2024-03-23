package com.samin.jobcenter.entity;

import lombok.Data;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "job_log")
public class JobLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 定时任务 ID
     */
    @Comment("定时任务 ID")
    private Integer jobId;

    /**
     * 执行结果
     */
    @Comment("执行结果")
    private Boolean result;

    /**
     * 错误信息
     */
    @Comment("错误信息")
    private String errorMsg;

    /**
     * 实际开始执行时间
     */
    @Comment("实际开始执行时间")
    private LocalDateTime startTime;

    /**
     * 实际结束执行时间
     */
    @Comment("实际结束执行时间")
    private LocalDateTime endTime;

    /**
     * 创建时间
     */
    @Comment("创建时间")
    private LocalDateTime createTime;
}
