package puzzle.q3;

import java.lang.management.ManagementFactory;

/**
 * 此模拟程序目的是为了观测多核 CPU 的运行负载情况
 *
 * @author samin
 * @date 2021-01-10
 */
public class Cpu100Run {

    /**
     * 记得加 volatile，不然程序会失效，参考JMM的可见性（未使用的时候，变量可能会在主内存、本地内存分别存放数据）
     */
    private static volatile Boolean SWITCH_BOOL = true;
    /**
     * 模拟的线程数，每个线程可以让一个 CPU 使用率高达 100%
     */
    private final static int RUN_THREAD_NUMS = 3;
    /**
     * 模拟程序运行时间，默认 5 秒
     */
    private final static long PROGRAM_RUNNING_TIME = 5 * 1000L;

    public static void main(String[] args) {
        System.out.println("Running at pid:" + ManagementFactory.getRuntimeMXBean().getName());

        // 建议设置不需要超过 `核数+1` ，次模拟基于 CPU密集型任务模型
        runThread(RUN_THREAD_NUMS);

        // 定时关闭程序
        new Thread(new ChangeSwitch()).start();

        while (SWITCH_BOOL) {
        }

        System.out.println("program stop.");
    }

    /**
     * 运行线程
     *
     * @param nums 启动的线程数
     */
    private static void runThread(int nums) {
        for (int i = 0; i < nums; i++) {
            new Thread(new Running100Thread(), "100%Thread-" + (i + 1)).start();
        }
    }

    /**
     * 模拟 CPU 占用率为 100% 的线程
     */
    private static class Running100Thread implements Runnable {

        @Override
        public void run() {
            System.out.println("Thread :" + Thread.currentThread().getName() + " start.");
            // 死循环，模拟 CPU 100%运行
            while (SWITCH_BOOL) {
                int sum = 1 + 1;
            }
            System.out.println("Thread :" + Thread.currentThread().getName() + " stop.");
        }
    }

    /**
     * 定时关闭程序，否则模拟程序无法停止自动停止
     */
    private static class ChangeSwitch implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println("System running ...");
                System.out.println("You can check the information by top behavioural.command with -Hp parameter !");

                Thread.sleep(PROGRAM_RUNNING_TIME);

                // 关闭程序
                SWITCH_BOOL = false;
                System.out.println("stop ...");
            } catch (Exception ignored) {
            }
        }
    }
}
