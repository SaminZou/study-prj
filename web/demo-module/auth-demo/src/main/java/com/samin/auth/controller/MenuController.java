package com.samin.auth.controller;

import com.samin.auth.service.MenuService;
import com.samin.auth.vo.base.BaseResp;
import com.samin.auth.vo.req.MenuPageReq;
import com.samin.auth.vo.req.MenuSaveReq;
import com.samin.auth.vo.req.PageReq;
import com.samin.auth.vo.resp.MenuResp;
import com.samin.auth.vo.resp.PageResp;
import java.util.List;
import javax.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 菜单控制类
 *
 * @author samin
 * @date 2023-07-23
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    @PostMapping("/page")
    public PageResp<MenuResp> page(@RequestBody MenuPageReq req) {
        return menuService.page(req);
    }

    @PostMapping("/save")
    public BaseResp<Void> save(@RequestBody List<MenuSaveReq> menuVos) {
        menuService.saveMenu(menuVos);
        return BaseResp.success();
    }

    @PostMapping("/delete/{id}")
    public BaseResp<Void> delete(@Min(value = 1, message = "最小") @PathVariable("id") Integer id) {
        menuService.delete(id);
        return BaseResp.success();
    }
}