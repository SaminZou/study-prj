package com.samin.usecase.strategyenhance.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service(ConponStrategyEnhanceEntry.COUPON_GOODS_COUPON)
public class GoodsCouponStrategyEnhance implements CouponStrategyEnhance {

  @Override
  public void checkTotal() {
    log.info("1. 查询商品优惠券剩余量");
  }

  @Override
  public void userCouponRel() {
    log.info("2. 绑定用户和商品优惠券关联关系");
  }

  @Override
  public String result() {
    return "商品优惠券";
  }

  @Override
  public void saveLog() {
    log.info("3. 存入日志");
  }
}
