package com.samin.usecase.strategyenhance.controller;

import com.samin.usecase.strategyenhance.service.StrategyGetCouponEnhanceService;
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
public class CouponEnhanceController {

  private final StrategyGetCouponEnhanceService strategyGetCouponEnhanceService;

  @GetMapping("/getCoupon3")
  public String getCoupon3(@RequestParam(value = "type") Integer type) {
    return strategyGetCouponEnhanceService.getCoupon(type);
  }
}
