package com.samin.usecase.strategyenhance.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("ShoppingCard")
public class ShoppingCardStrategyEnhance implements CouponStrategyEnhance {

    @Override
    public void checkTotal() {
        log.info("1. 查询购物卡剩余量");
    }

    @Override
    public void userCouponRel() {
        log.info("2. 绑定用户和购物卡关联关系");
    }


    @Override
    public String result() {
        return "购物卡";
    }

    @Override
    public void saveLog() {
        log.info("3. 存入日志");
    }
}
