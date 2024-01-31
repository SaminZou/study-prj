package com.samin.jpa.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class UserDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    /**
     * 指定字段名称为 sex，默认不指定为和字段保持一致
     */
    @Column(name = "sex")
    private Integer sex;

    @Column(name = "mobile")
    private String mobile;

    /**
     * 忽略持久化
     */
    @Transient
    private String other;
}
