package com.samin.auth.entity;

import lombok.Data;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "system_log", schema = "system")
public class SystemLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Comment("操作用户id")
    private Integer userId;

    @Comment("日志类型")
    private String type;

    @Comment("操作 ip")
    private String ip;

    @Comment("操作时间")
    private LocalDateTime createTime;
}
