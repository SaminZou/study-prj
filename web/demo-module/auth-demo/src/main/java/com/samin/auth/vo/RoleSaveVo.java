package com.samin.auth.vo;

import lombok.Data;

import java.util.List;

@Data
public class RoleSaveVo {

    private Integer id;

    private String name;

    private String code;

    private String remark;

    private List<Integer> menus;
}
