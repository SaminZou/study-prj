package com.samin.auth.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Comment;

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
