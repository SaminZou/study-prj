package com.samin.auth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * 业务控制器
 *
 * @author samin
 * @date 2022-08-08
 */
@RestController
public class BusinessController {

    /**
     * 可以访问成功
     *
     * @return
     */
    @PreAuthorize("@permissionService.access('get:full:test')")
    @GetMapping("/test")
    public HashMap<String, String> test() {
        return new HashMap<String, String>(1) {
            {
                put("msg", "访问成功");
            }
        };
    }

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