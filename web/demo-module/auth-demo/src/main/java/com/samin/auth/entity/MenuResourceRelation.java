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

    // TODO 使用 code
    @Comment("菜单id")
    private Integer menuId;

    // TODO 使用 urn
    @Comment("资源id")
    private Integer resourceId;

    @Comment("创建时间")
    private LocalDateTime createTime;
}
