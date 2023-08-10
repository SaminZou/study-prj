package com.samin.auth.vo.req;

import lombok.Data;

import java.util.List;

@Data
public class UserSaveReq {

    private Integer id;

    private String mobile;

    private String password;

    private String icon;

    private String email;

    private String nickName;

    private String note;

    private List<Integer> roles;
}
