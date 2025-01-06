package com.samin.usecase.strategy.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TraditionGetCouponService {

  /**
   * 传统获取优惠券的方式主要存在问题：
   *
   * <p>- 可读性差
   *
   * <p>- 代码太长，修改不便，可维护性低
   *
   * <p>- 如下 1 和 2 动作其实是抽象必须操作的步骤，如果想要批量去掉或者修改增加类似日志通用逻辑就会造成重复工作量，切容易出错
   *
   * @param type
   * @return
   */
  public String getCoupon(int type) {
    switch (type) {
      case 1:
        log.info("1. 查询红包剩余量");
        log.info("2. 绑定用户和红包关联关系");
        return "红包";
      case 2:
        log.info("1. 查询商品优惠券剩余量");
        log.info("2. 绑定用户和商品优惠券关联关系");
        return "商品优惠券";
      case 3:
        log.info("1. 查询会员券剩余量");
        log.info("2. 绑定用户和会员券关联关系");
        return "会员券";
      case 4:
        log.info("1. 查询购物卡剩余量");
        log.info("2. 绑定用户和购物卡关联关系");
        return "购物卡";
      default:
        log.info("查不到");
        return "无法找到对应类型的优惠券";
    }
  }
}
