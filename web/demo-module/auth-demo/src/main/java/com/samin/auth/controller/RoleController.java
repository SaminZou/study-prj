package com.samin.auth.controller;

import com.samin.auth.service.RoleService;
import com.samin.auth.vo.BaseResp;
import com.samin.auth.vo.RoleSaveResp;
import com.samin.auth.vo.RoleSaveVo;
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

    @PostMapping("/save")
    public BaseResp<RoleSaveResp> save(@RequestBody RoleSaveVo roleSaveVo) {
        return BaseResp.success(roleService.saveRole(roleSaveVo));
    }
}
