package com.samin.usecase.strategyenhance.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("Voucher")
public class VoucherStrategyEnhance implements CouponStrategyEnhance {

    @Override
    public void checkTotal() {
        log.info("1. 查询会员券剩余量");
    }

    @Override
    public void userCouponRel() {
        log.info("2. 绑定用户和会员券关联关系");
    }

    @Override
    public String result() {
        return "会员券";
    }

    @Override
    public void saveLog() {
        log.info("3. 存入日志");
    }
}
