package com.samin.auth.entity;

import lombok.Data;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "auth_role_menu_relation", schema = "auth")
public class RoleMenuRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Comment("角色id")
    private Integer roleId;

    @Comment("菜单id")
    private Integer menuId;

    @Comment("创建时间")
    private LocalDateTime createTime;
}
