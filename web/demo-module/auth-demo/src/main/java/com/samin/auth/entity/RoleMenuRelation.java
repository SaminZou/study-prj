package com.samin.auth.entity;

import lombok.Data;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "auth_role_menu_relation", schema = "auth")
public class RoleMenuRelation implements Serializable {

    public static RoleMenuRelation getInstance(Integer roleId, Integer menuId) {
        RoleMenuRelation instance = new RoleMenuRelation();

        instance.setRoleId(roleId);
        instance.setMenuId(menuId);
        instance.setCreateTime(LocalDateTime.now());

        return instance;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // TODO 使用 code
    @Comment("角色id")
    private Integer roleId;

    // TODO 使用 code
    @Comment("菜单id")
    private Integer menuId;

    @Comment("创建时间")
    private LocalDateTime createTime;
}
