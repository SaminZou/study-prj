package com.samin.usecase.strategy.service;

import com.samin.usecase.strategy.common.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StrategyGetCouponService {

    /**
     * 策略方式获取优惠券的方式主要存在问题：
     * <p> - 本质上还是 if else
     *
     * @param type
     * @return
     */
    public String getCoupon(int type) {
        CouponStrategy strategy = context(type);
        strategy.checkTotal();
        strategy.userCouponRel();
        strategy.saveLog();
        return strategy.result();
    }

    public CouponStrategy context(int type) {
        switch (type) {
            case 1:
                return new RedEnvelopeStrategy();
            case 2:
                return new GoodsCouponStrategy();
            case 3:
                return new VoucherStrategy();
            case 4:
                return new ShoppingCardStrategy();
            default:
                log.info("查不到");
                return null;
        }
    }
}
