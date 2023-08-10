package com.samin.auth.vo.req;

import lombok.Data;

import java.util.List;

@Data
public class RoleSaveReq {

    private Integer id;

    private String name;

    private String code;

    private String remark;

    private List<Integer> menus;
}
