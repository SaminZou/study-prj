package com.samin.usecase.repo.entity;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "table_user")
public class TableUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String userName;

    private String userDesc;
}
