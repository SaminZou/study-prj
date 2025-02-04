package com.samin.auth.vo.resp;

import com.samin.auth.entity.Role;
import com.samin.auth.entity.RoleMenuRelation;
import com.samin.auth.util.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

@ApiModel("角色响应体")
@Data
public class RoleResp {

    public static RoleResp getInstance(Role role, List<RoleMenuRelation> list) {
        RoleResp resp = new RoleResp();

        resp.setId(role.getId());
        resp.setName(role.getName());
        resp.setCode(role.getCode());
        resp.setRemark(role.getRemark());
        resp.setCreateTime(DateUtil.getDisplayTime(role.getCreateTime()));
        resp.setUpdateTime(DateUtil.getDisplayTime(role.getUpdateTime()));
        resp.setMenus(list.stream()
                          .map(RoleMenuRelation::getMenuCode)
                          .collect(Collectors.toList()));

        return resp;
    }

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("角色名")
    private String name;

    @ApiModelProperty("角色编码")
    private String code;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("创建时间")
    private String createTime;

    @ApiModelProperty("更新时间")
    private String updateTime;

    @ApiModelProperty("菜单列表")
    private List<String> menus;
}