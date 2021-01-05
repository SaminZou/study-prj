package puzzle.q3;

import java.lang.management.ManagementFactory;

/** 此模拟程序目的是为了观测多核 CPU 的运行负载情况 */
public class Cpu100Run {
    // 记得加 volatile，不然程序会失效，参考JMM的可见性（未使用的时候，变量可能会在主内存、本地内存分别存放数据）
    public static volatile Boolean switchBool = true;

    public static void main(String[] args) {
        System.out.println("Running at pid:" + ManagementFactory.getRuntimeMXBean().getName());

        // 建议设置不需要超过 `核数+1` ，次模拟基于 CPU密集型任务模型
        runThread(6);

        // 定时关闭程序
        new Thread(new ChangeSwitch()).start();

        while (switchBool) {}

        System.out.println("program stop.");
    }

    private static void runThread(Integer nums) {
        for (int i = 0; i < nums; i++) {
            new Thread(new Running100Thread(), "100%Thread").start();
        }
    }

    private static class Running100Thread implements Runnable {
        @Override
        public void run() {
            System.out.println("thread :" + Thread.currentThread().getName() + " start.");
            // 死循环，模拟 CPU 100%运行
            while (switchBool) {
                int sum = 1 + 1;
            }
            System.out.println("thread :" + Thread.currentThread().getName() + " stop.");
        }
    }

    // 定时关闭程序，否则模拟程序无法停止自动停止
    private static class ChangeSwitch implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("System running ...");
                System.out.println(
                        "You can check the information by top behavioural.command with -Hp parameter !");
                // 保持10秒运行时间
                Thread.sleep(100000);
                // 关闭程序
                switchBool = false;
                System.out.println("stop ...");
            } catch (Exception ignored) {
            }
        }
    }
}
