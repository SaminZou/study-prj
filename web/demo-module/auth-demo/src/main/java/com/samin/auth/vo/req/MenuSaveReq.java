package com.samin.auth.vo.req;

import lombok.Data;

@Data
public class MenuSaveReq {

    private Integer id;

    private Integer parentId;

    private String name;

    private String code;

    private Integer sort;

    private String icon;

    private String remark;

    private Integer hidden;
}
