package com.samin.usecase.strategy.common;

/**
 * 便于维护抽象统一的业务逻辑
 *
 * @author samin
 * @date 2023-06-12
 */
public interface CouponStrategy {

    void checkTotal();

    void userCouponRel();

    String result();

    void saveLog();
}
