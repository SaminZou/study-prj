package com.samin.coding.Q13;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicUseCase {
    public static AtomicInteger sumsInteger = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 5; i++) {
            new Thread(
                            () -> {
                                for (int j = 0; j < 5000; j++) {
                                    sumsInteger.addAndGet(1); // 用原子类操作数据，也可以保证线程安全
                                }
                            })
                    .start();
        }

        Thread.sleep(5000); // 等待全部线程执行完毕，结果观测才是有意义的

        System.out.println("sums: " + sumsInteger);
        System.out.println("---------------------------------------");
    }
}
