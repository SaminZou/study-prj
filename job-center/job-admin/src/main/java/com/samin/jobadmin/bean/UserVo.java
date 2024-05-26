package com.samin.jobadmin.bean;

import lombok.Data;

@Data
public class UserVo {

    private Integer id;

    private String loginName;

    private String password;

    private Integer isEnable;

    private String createTime;

    private String updateTime;
}