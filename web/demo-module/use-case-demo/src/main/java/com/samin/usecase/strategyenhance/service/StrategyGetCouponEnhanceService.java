package com.samin.usecase.strategyenhance.service;

import com.samin.usecase.strategyenhance.common.ConponEntryStrategyEnhance;
import com.samin.usecase.strategyenhance.common.CouponStrategyEnhance;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StrategyGetCouponEnhanceService {

    private final ConponEntryStrategyEnhance conponEntryStrategyEnhance;

    public String getCoupon(int type) {
        CouponStrategyEnhance strategy = conponEntryStrategyEnhance.context(type);
        strategy.checkTotal();
        strategy.userCouponRel();
        strategy.saveLog();
        return strategy.result();
    }
}
