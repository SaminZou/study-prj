package com.samin.Q18;

import java.lang.management.ManagementFactory;

public class Cpu100Run {
    // 用于定时关闭程序，不然会一直让CPU 100%运行
    // 记得家volatile，不然程序会失效，参考JMM的可见性
    public static volatile Boolean switchBool = true;

    public static class ChangeSwitch implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("System running ...");
                System.out.println("You can check the information by top command !");
                // 保持10秒运行时间
                Thread.sleep(10000);
                // 关闭程序
                switchBool = false;
                System.out.println("stop ...");
            } catch (Exception e) {
            }
        }
    }

    public static class Running100Thread implements Runnable {
        @Override
        public void run() {
            System.out.println("thread :" + Thread.currentThread().getName() + " start.");
            // 死循环，模拟CPU 100%运行
            while (switchBool) {
                int sum = 1 + 1;
            }
            System.out.println("thread :" + Thread.currentThread().getName() + " stop.");
        }
    }

    public static void main(String[] args) {
        System.out.println("Running at pid:" + ManagementFactory.getRuntimeMXBean().getName());

        // 此行代码执行会让CPU使用率达到200%，得益于多核处理器，也可以复制多个，将会观测到更高的比例
        new Thread(new Running100Thread(), "100%Thread").start();

        // 定时关闭程序
        new Thread(new ChangeSwitch()).start();

        // 死循环，模拟CPU 100%运行
        while (switchBool) {
            int sum = 1 + 1;
        }
        System.out.println("program stop.");
    }
}
