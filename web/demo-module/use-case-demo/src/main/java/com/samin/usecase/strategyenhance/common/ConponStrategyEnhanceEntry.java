package com.samin.usecase.strategyenhance.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ConponStrategyEnhanceEntry {

    public final static String COUPON_RED_ENVELOPE = "RedEnvelope";
    public final static String COUPON_GOODS_COUPON = "GoodsCoupon";
    public final static String COUPON_VOUCHER = "Voucher";
    public final static String COUPON_SHOPPING_CARD = "ShoppingCard";

    /**
     * spring 特性
     */
    private final Map<String, CouponStrategyEnhance> couponStrategyMap;
    /**
     * 真实业务场景，这个会作为入参
     */
    private static final Map<Integer, String> mapping = new HashMap<Integer, String>(4) {
        {
            put(1, COUPON_RED_ENVELOPE);
            put(2, COUPON_GOODS_COUPON);
            put(3, COUPON_VOUCHER);
            put(4, COUPON_SHOPPING_CARD);
        }
    };

    public CouponStrategyEnhance context(int type) {
        return couponStrategyMap.get(mapping.get(type));
    }
}
