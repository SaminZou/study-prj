package com.samin.usecase.repo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "table_user")
public class UserDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String userName;

    private String userDesc;
}
