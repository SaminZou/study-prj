package com.samin.auth.controller;

import com.samin.auth.service.RoleService;
import com.samin.auth.vo.base.BaseResp;
import com.samin.auth.vo.req.PageReq;
import com.samin.auth.vo.resp.PageResp;
import com.samin.auth.vo.resp.RoleResp;
import com.samin.auth.vo.resp.RoleSaveResp;
import com.samin.auth.vo.req.RoleSaveReq;
import com.samin.auth.vo.resp.UserResp;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

/**
 * 角色控制类
 *
 * @author samin
 * @date 2023-07-23
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/page")
    public PageResp<RoleResp> page(@RequestBody PageReq req) {
        return roleService.page(req);
    }

    @PostMapping("/save")
    public BaseResp<RoleSaveResp> save(@RequestBody RoleSaveReq roleSaveReq) {
        return BaseResp.success(roleService.saveRole(roleSaveReq));
    }

    @PostMapping("/delete/{id}")
    public BaseResp<Void> delete(@Min(value = 1,message = "最小") @PathVariable("id") Integer id) {
        roleService.delete(id);
        return BaseResp.success();
    }
}