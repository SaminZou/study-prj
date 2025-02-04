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
@Table(name = "auth_role", schema = "auth")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Comment("名称")
    private String name;

    @Comment("编码")
    private String code;

    @Comment("备注")
    private String remark;

    @Comment("创建时间")
    private LocalDateTime createTime = LocalDateTime.now();

    @Comment("更新时间")
    private LocalDateTime updateTime;

    @Comment("状态（0：禁用，1启用）")
    private Integer status;
}
