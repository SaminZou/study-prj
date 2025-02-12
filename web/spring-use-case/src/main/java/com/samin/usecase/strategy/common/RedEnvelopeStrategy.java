package com.samin.usecase.strategy.common;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RedEnvelopeStrategy implements CouponStrategy {

    @Override
    public void checkTotal() {
        log.info("1. 查询红包剩余量");
    }

    @Override
    public void userCouponRel() {
        log.info("2. 绑定用户和红包关联关系");
    }

    @Override
    public String result() {
        return "红包";
    }

    @Override
    public void saveLog() {
        log.info("3. 存入日志");
    }
}
