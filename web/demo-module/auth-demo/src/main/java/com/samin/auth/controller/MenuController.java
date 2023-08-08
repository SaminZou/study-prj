package com.samin.auth.controller;

import com.samin.auth.service.MenuService;
import com.samin.auth.vo.BaseResp;
import com.samin.auth.vo.MenuSaveVo;
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

    @PostMapping("/save")
    public BaseResp<Void> save(@RequestBody List<MenuSaveVo> menuVos) {
        menuService.saveMenu(menuVos);
        return BaseResp.success();
    }
}
