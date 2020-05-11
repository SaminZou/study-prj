package com.samin.Q13;

import java.util.concurrent.atomic.AtomicInteger;

public class SynchronizedUseCase {
    public static int sums = 0;

    // 1. 同步代码块
    public void method(){
        synchronized (this){
            System.out.println("synchoronized 代码块");
        }
    }

    public static void main(String[] args) throws Exception {



        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5000; j++) {
                    synchronized (SynchronizedUseCase.class) { // 加锁控制
                        sums += 1;
                    }
                }
            }).start();
        }

        Thread.sleep(5000); // 等待全部线程执行完毕，结果观测才是有意义的

        System.out.println("sums: " + sums);
        System.out.println("---------------------------------------");
    }
}
