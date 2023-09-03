package com.samin.auth.entity;

import java.io.Serializable;
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
@Table(name = "auth_role_menu_relation", schema = "auth")
public class RoleMenuRelation implements Serializable {

    public static RoleMenuRelation getInstance(String roleCode, String menuCode) {
        RoleMenuRelation instance = new RoleMenuRelation();

        instance.setRoleCode(roleCode);
        instance.setMenuCode(menuCode);
        instance.setCreateTime(LocalDateTime.now());

        return instance;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Comment("角色 code")
    private String roleCode;

    @Comment("菜单 code")
    private String menuCode;

    @Comment("创建时间")
    private LocalDateTime createTime;
}
