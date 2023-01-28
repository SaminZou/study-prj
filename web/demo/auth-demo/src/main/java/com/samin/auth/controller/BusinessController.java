package com.samin.auth.controller;

import java.util.HashMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 业务控制器
 *
 * @author samin
 * @date 2022-08-08
 */
@RestController
public class BusinessController {

    @GetMapping("/test")
    public HashMap<String, String> test() {
        return new HashMap<String, String>(1) {
            {
                put("msg", "访问成功");
            }
        };
    }
}