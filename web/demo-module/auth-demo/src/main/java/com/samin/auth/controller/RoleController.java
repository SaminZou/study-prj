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
}