package com.samin.auth.entity;

import lombok.Data;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "auth_menu", schema = "auth")
public class Menu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Comment("父级id")
    private Integer parentId;

    @Comment("菜单名称")
    private String name;

    @Comment("菜单级数")
    private Integer level;

    @Comment("菜单排序")
    private Integer sort;

    @Comment("图标")
    private String icon;

    @Comment("备注")
    private String remark;

    @Comment("状态（0：禁用，1：启用）")
    private Integer status;

    @Comment("是否隐藏（0：显示，1：隐藏）")
    private Integer hidden;

    @Comment("创建时间")
    private LocalDateTime createTime;

    @Comment("更新时间")
    private LocalDateTime updateTime;
}
