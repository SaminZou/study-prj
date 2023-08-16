package com.samin.auth.vo.resp;

import com.samin.auth.entity.Role;
import com.samin.auth.entity.RoleMenuRelation;
import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class RoleResp {

    private Integer id;
    private String name;
    private String code;
    private String remark;
    private String createTime;
    private String updateTime;
    private List<Integer> menus;

    public static RoleResp getInstance(Role role, List<RoleMenuRelation> list) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        RoleResp resp = new RoleResp();

        resp.setId(role.getId());
        resp.setName(role.getName());
        resp.setCode(role.getCode());
        resp.setRemark(role.getRemark());
        resp.setCreateTime(dtf.format(role.getCreateTime()));
        resp.setUpdateTime(Objects.nonNull(role.getUpdateTime()) ? dtf.format(role.getUpdateTime()) : "");
        resp.setMenus(list.stream()
                .map(RoleMenuRelation::getMenuId)
                .collect(Collectors.toList()));

        return resp;
    }
}