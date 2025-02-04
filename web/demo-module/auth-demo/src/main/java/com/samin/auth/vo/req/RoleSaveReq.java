package com.samin.auth.vo.req;

import java.util.List;
import lombok.Data;

@Data
public class RoleSaveReq {

    private Integer id;

    private String name;

    private String code;

    private String remark;

    private List<String> menus;
}
