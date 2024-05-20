package com.samin.jobadmin.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Comment("登录名")
    private String loginName;

    @Comment("密码")
    private String password;

    @Comment("是否停用（0：否，1：是）")
    private Integer isEnable;

    @Comment("创建时间")
    private LocalDateTime createTime;

    @Comment("更新时间")
    private LocalDateTime updateTime;
}