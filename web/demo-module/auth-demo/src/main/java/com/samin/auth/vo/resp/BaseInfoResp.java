package com.samin.auth.vo.resp;

import com.samin.auth.entity.Menu;
import com.samin.auth.entity.Resource;
import com.samin.auth.entity.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 基础信息返回
 * <p>
 * Description: 基础信息返回
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2023-08-25
 */
@ApiModel("基础信息返回")
@Data
public class BaseInfoResp {

    @ApiModelProperty("角色列表")
    private List<Role> roles;

    @ApiModelProperty("菜单列表")
    private List<Menu> menus;

    @ApiModelProperty("资源列表")
    private List<Resource> resources;
}
