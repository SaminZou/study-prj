package com.samin.usecase.param;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 参数和实例
 * <p>
 * Description: 参数和实例
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2023-12-11
 */
@Slf4j
@RestController
public class ParamAndInstanceController {

    /**
     * {"roleIds": null} 接收为: null
     * <p> {"roleIds": []} 接收为: []
     * <p> {} 接收为: null
     *
     * @param req
     */
    @PostMapping("/updateRoleIds")
    public void updateRoleIds(@RequestBody ParamAndInstanceObject.ParamObj1 req) {
        log.info("updateRoleIds: {}", req);
    }

    /**
     * {"roleIds": null} 接收为: null
     * <p> {"roleIds": []} 接收为: []
     * <p> {} 接收为: []
     *
     * @param req
     */
    @PostMapping("/updateRoleIds2")
    public void updateRoleIds2(@RequestBody ParamAndInstanceObject.ParamObj2 req) {
        log.info("updateRoleIds: {}", req);
    }

    /**
     * 入参为 parkCode=test&parkCode=test2&parkCode=test3
     * 打印结果为 test,test2,test3
     * @param parkCode
     */
    @GetMapping("/multipleSameParams")
    public void multipleSameParams(String parkCode) {
        log.info("parkCode: {}", parkCode);
    }
}
