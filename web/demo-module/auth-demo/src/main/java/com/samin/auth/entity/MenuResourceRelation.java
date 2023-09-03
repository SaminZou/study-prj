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
@Table(name = "auth_menu_resource_relation", schema = "auth")
public class MenuResourceRelation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Comment("菜单 code")
    private String menuCode;

    @Comment("资源 urn")
    private String resourceUrn;

    @Comment("创建时间")
    private LocalDateTime createTime;
}
