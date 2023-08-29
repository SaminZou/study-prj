package com.samin.auth.controller;

import com.samin.auth.service.RoleService;
import com.samin.auth.vo.base.BaseResp;
import com.samin.auth.vo.req.PageReq;
import com.samin.auth.vo.req.RoleSaveReq;
import com.samin.auth.vo.resp.PageResp;
import com.samin.auth.vo.resp.RoleResp;
import com.samin.auth.vo.resp.RoleSaveResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色控制类
 *
 * @author samin
 * @date 2023-07-23
 */
@Api("角色控制类")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    @ApiOperation("分页查询")
    @PostMapping("/page")
    public PageResp<RoleResp> page(@RequestBody PageReq req) {
        return roleService.page(req);
    }

    @ApiOperation("保存")
    @PostMapping("/save")
    public BaseResp<RoleSaveResp> save(@RequestBody RoleSaveReq roleSaveReq) {
        return BaseResp.success(roleService.saveRole(roleSaveReq));
    }

    @ApiOperation("删除")
    @PostMapping("/delete/{id}")
    public BaseResp<Void> delete(@Min(value = 1, message = "最小") @PathVariable("id") Integer id) {
        roleService.delete(id);
        return BaseResp.success();
    }
}