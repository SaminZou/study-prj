package com.samin.auth.vo;

import lombok.Data;

import java.util.List;

@Data
public class RoleVo {

    private Integer id;

    private String name;

    private String code;

    private String remark;

    private String createTime;

    private String updateTime;

    private Integer status;

    private List<Integer> menus;
}
