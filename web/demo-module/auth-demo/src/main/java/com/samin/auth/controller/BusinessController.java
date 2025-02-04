package com.samin.auth.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 业务控制器
 * <p>
 * Description: 业务控制器
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2023-08-25
 */
@Api("业务控制器")
@RestController
public class BusinessController {

    /**
     * 可以访问成功
     *
     * @return
     */
    @ApiOperation("测试接口")
    @PreAuthorize("@permissionService.access('get:full:test')")
    @GetMapping("/test")
    public HashMap<String, String> test() {
        return new HashMap<String, String>(1) {
            {
                put("msg", "访问成功");
            }
        };
    }

    @ApiOperation("测试接口2")
    @PreAuthorize("@permissionService.access('get:full:test2')")
    @GetMapping("/test2")
    public HashMap<String, String> test2() {
        return new HashMap<String, String>(1) {
            {
                put("msg", "访问成功");
            }
        };
    }
}