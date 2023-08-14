package com.samin.auth.controller;

import com.samin.auth.service.MenuService;
import com.samin.auth.vo.base.BaseResp;
import com.samin.auth.vo.req.MenuSaveReq;
import com.samin.auth.vo.req.PageReq;
import com.samin.auth.vo.resp.MenuResp;
import com.samin.auth.vo.resp.PageResp;
import com.samin.auth.vo.resp.RoleResp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 菜单控制类
 *
 * @author samin
 * @date 2023-07-23
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    @PostMapping("/page")
    public PageResp<MenuResp> page(@RequestBody PageReq req) {
        return menuService.page(req);
    }

    @PostMapping("/save")
    public BaseResp<Void> save(@RequestBody List<MenuSaveReq> menuVos) {
        menuService.saveMenu(menuVos);
        return BaseResp.success();
    }
}