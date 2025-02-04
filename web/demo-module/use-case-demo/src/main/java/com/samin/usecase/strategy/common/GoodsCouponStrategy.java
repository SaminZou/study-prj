package com.samin.usecase.strategy.common;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GoodsCouponStrategy implements CouponStrategy {

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
