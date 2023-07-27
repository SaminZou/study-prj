package com.samin.auth.entity;

import lombok.Data;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "auth_user", schema = "auth")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Comment("电话")
    private String mobile;

    @Comment("密码")
    private String password;

    @Comment("头像")
    private String icon;

    @Comment("电子邮件")
    private String email;

    @Comment("昵称")
    private String nickName;

    @Comment("个人签名")
    private String note;

    @Comment("创建时间")
    private LocalDateTime createTime;

    @Comment("更新时间")
    private LocalDateTime updateTime;

    @Comment("最后登录时间")
    private LocalDateTime lastLoginTime;

    @Comment("状态（0：禁用，1启用）")
    private Integer status;
}
