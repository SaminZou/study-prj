package com.samin.auth.controller;

import com.samin.auth.service.BaseInfoService;
import com.samin.auth.vo.base.BaseResp;
import com.samin.auth.vo.resp.BaseInfoResp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 基础信息控制器
 * <p>
 * Description: 基础信息控制器
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2023-08-25
 */
@RestController
@RequiredArgsConstructor
//@Api("基础信息控制器")
public class BaseInfoController {

    private final BaseInfoService baseInfoService;

    @PostMapping(value = "/base_info")
    public BaseResp<BaseInfoResp> baseInfo() {
        return BaseResp.success(baseInfoService.baseInfo());
    }
}
