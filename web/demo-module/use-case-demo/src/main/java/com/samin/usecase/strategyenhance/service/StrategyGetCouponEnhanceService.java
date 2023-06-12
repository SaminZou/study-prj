package com.samin.usecase.strategyenhance.service;

import com.samin.usecase.strategyenhance.common.CouponStrategyEnhance;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class StrategyGetCouponEnhanceService {

    /**
     * spring 特性
     */
    private final Map<String, CouponStrategyEnhance> couponStrategyMap;
    /**
     * 真实业务场景，这个会作为入参
     */
    private final Map<Integer, String> BIZ_PARAM = new HashMap<>() {
        {
            put(1, "RedEnvelope");
            put(2, "GoodsCoupon");
            put(3, "Voucher");
            put(4, "ShoppingCard");
        }
    };

    public String getCoupon(int type) {
        CouponStrategyEnhance strategy = context(type);
        strategy.checkTotal();
        strategy.userCouponRel();
        strategy.saveLog();
        return strategy.result();
    }

    public CouponStrategyEnhance context(int type) {
        return couponStrategyMap.get(BIZ_PARAM.get(type));
    }
}
