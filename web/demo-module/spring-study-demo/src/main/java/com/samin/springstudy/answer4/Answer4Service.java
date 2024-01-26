package com.samin.springstudy.answer4;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * TODO 调试
 * <p>
 * Description: TODO 调试
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2024-01-26
 */
@Service
public class Answer4Service {

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void main() {
        System.out.println(TransactionSynchronizationManager.getCurrentTransactionName());
        System.out.println("执行业务操作主要 SQL");
        busi1();
        busi2();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void busi1() {
        System.out.println(TransactionSynchronizationManager.getCurrentTransactionName());
        // 创建新事务
        System.out.println("加入当前事务，执行业务 SQL");
    }

    @Transactional(propagation = Propagation.NEVER)
    public void busi2() {
        System.out.println(TransactionSynchronizationManager.getCurrentTransactionName());
        // 不创建事务
        System.out.println("执行业务 SQL");
    }
}
