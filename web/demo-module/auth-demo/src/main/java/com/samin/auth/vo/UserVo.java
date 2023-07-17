package com.samin.auth.vo;

import lombok.Data;

@Data
public class UserVo {

    private Integer id;

    private String mobile;

    private String password;

    private String icon;

    private String email;

    private String nickName;

    private String note;

    private String createTime;

    private String updateTime;

    private String lastLoginTime;

    private Integer status;
}
