package com.samin.usecase.strategyenhance.common;

/**
 * 便于维护抽象统一的业务逻辑
 *
 * @author samin
 * @date 2023-06-12
 */
public interface CouponStrategyEnhance {

  /** 检查剩余数量 */
  void checkTotal();

  /** 绑定关系 */
  void userCouponRel();

  /**
   * 返回结果
   *
   * @return
   */
  String result();

  /** 保存日志 */
  void saveLog();
}
