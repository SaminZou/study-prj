package com.samin.mybatis.model;

import java.time.LocalDate;
import lombok.Data;

@Data
public class UserVO {

    private Integer id;

    private String name;

    private Integer sex;

    private String mobile;

    private String email;

    private LocalDate date;

    private String foo;
}
