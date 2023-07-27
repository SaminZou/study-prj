package com.samin.auth.entity;

import lombok.Data;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "auth_menu_resource_relation", schema = "auth")
public class MenuResourceRelation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Comment("菜单id")
    private Integer menuId;

    @Comment("资源id")
    private Integer resourceId;

    @Comment("创建时间")
    private LocalDateTime createTime;
}
