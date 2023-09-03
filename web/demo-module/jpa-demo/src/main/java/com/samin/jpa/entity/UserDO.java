package com.samin.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;

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
