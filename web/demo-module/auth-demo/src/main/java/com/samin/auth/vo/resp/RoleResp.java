package com.samin.auth.vo.resp;

import com.samin.auth.entity.Role;
import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Data
public class RoleResp {

    private Integer id;
    private String name;
    private String code;
    private String remark;
    private String createTime;
    private String updateTime;
    private Integer status;

    public static RoleResp getInstance(Role role) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        RoleResp resp = new RoleResp();

        resp.id = role.getId();
        resp.name = role.getName();
        resp.code = role.getCode();
        resp.remark = role.getRemark();
        resp.createTime = dtf.format(role.getCreateTime());
        resp.updateTime = Objects.nonNull(role.getUpdateTime()) ? dtf.format(role.getUpdateTime()) : "";
        resp.status = role.getStatus();

        return resp;
    }
}