package com.samin.auth.controller;

import com.samin.auth.service.BaseInfoService;
import com.samin.auth.vo.resp.BaseInfoResp;
import com.samin.auth.vo.base.BaseResp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 菜单控制器
 *
 * @author samin
 * @date 2023-07-19
 */
@RestController
@RequiredArgsConstructor
public class BaseInfoController {

    private final BaseInfoService baseInfoService;

    @PostMapping(value = "/base_info")
    public BaseResp<BaseInfoResp> baseInfo() {
        return BaseResp.success(baseInfoService.baseInfo());
    }
}
