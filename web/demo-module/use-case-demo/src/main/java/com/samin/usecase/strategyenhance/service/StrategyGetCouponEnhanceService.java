package com.samin.usecase.strategyenhance.service;

import com.samin.usecase.strategyenhance.common.ConponStrategyEnhanceEntry;
import com.samin.usecase.strategyenhance.common.CouponStrategyEnhance;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StrategyGetCouponEnhanceService {

    private final ConponStrategyEnhanceEntry conponStrategyEnhanceEntry;

    public String getCoupon(int type) {
        CouponStrategyEnhance strategy = conponStrategyEnhanceEntry.context(type);
        strategy.checkTotal();
        strategy.userCouponRel();
        strategy.saveLog();
        return strategy.result();
    }
}
