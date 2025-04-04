package com.samin.usecase.biztest;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "table_config")
public class TableConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String code;

    private String value;
}
