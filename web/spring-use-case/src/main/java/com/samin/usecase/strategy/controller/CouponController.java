package com.samin.usecase.strategy.controller;

import com.samin.usecase.strategy.service.StrategyGetCouponService;
import com.samin.usecase.strategy.service.TraditionGetCouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 假设逻辑，根据入参优惠券类型，提供用户获取优惠券的接口
 *
 * @author samin
 * @date 2023-06-12
 */
@RestController
@RequiredArgsConstructor
public class CouponController {

    private final TraditionGetCouponService traditionGetCouponService;
    private final StrategyGetCouponService strategyGetCouponService;

    @GetMapping("/getCoupon1")
    public String getCoupon1(@RequestParam(value = "type") Integer type) {
        return traditionGetCouponService.getCoupon(type);
    }

    @GetMapping("/getCoupon2")
    public String getCoupon2(@RequestParam(value = "type") Integer type) {
        return strategyGetCouponService.getCoupon(type);
    }
}
