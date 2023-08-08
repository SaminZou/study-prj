package com.samin.auth.vo;

import lombok.Data;

import java.util.List;

@Data
public class UserSaveVo {

    private Integer id;

    private String mobile;

    private String password;

    private String icon;

    private String email;

    private String nickName;

    private String note;

    private List<Integer> roles;
}
