package com.samin.auth.entity;

import lombok.Data;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "auth_user_role_relation", schema = "auth")
public class UserRoleRelation {

    public static UserRoleRelation getInstance(Integer userId, String roleCode) {
        UserRoleRelation instance = new UserRoleRelation();

        instance.setUserId(userId);
        instance.setRoleCode(roleCode);
        instance.setCreateTime(LocalDateTime.now());

        return instance;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Comment("用户id")
    private Integer userId;

    @Comment("角色 code")
    private String roleCode;

    @Comment("创建时间")
    private LocalDateTime createTime;
}
