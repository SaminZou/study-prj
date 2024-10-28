package concurrent;

import java.util.Random;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 停止一个正在运行的线程
 *
 * @author samin
 * @date 2023-02-13
 */
public class StopThreadUseCase {

    public static void main(String[] args) {
        // 停止正在运行的线程
        // stopThreadV1();
        // stopThreadV2();
        // 停止沉睡中的线程
        stopSleepThread();
    }

    static void stopThreadV1() {
        TaskThread taskThread = new TaskThread();
        Thread thread = new Thread(taskThread, "taskThread");
        thread.start();
        try {
            Thread.sleep(100);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * stopThread 方法的使用线程池的实现
     */
    static void stopThreadV2() {
        TaskThread taskThread = new TaskThread();

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(9, 20, 10, TimeUnit.MINUTES, new LinkedBlockingQueue<>(100), r -> new Thread(r, "show-the-error-thread-" + new Random().nextInt(999)));
        Future<?> future = poolExecutor.submit(taskThread);

        try {
            Thread.sleep(100);

            future.cancel(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            poolExecutor.shutdown();
        }
    }

    static void stopSleepThread() {
        SleepThread sleepThread = new SleepThread();
        Thread thread = new Thread(sleepThread, "sleepThread");
        thread.start();
        thread.interrupt();
    }

    static class SleepThread implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println("线程开始。。。");
                Thread.sleep(200000);
                System.out.println("线程结束。");
            } catch (InterruptedException e) {
                System.out.println("在沉睡中被停止, 进入catch， 调用isInterrupted()方法的结果是：" + Thread.currentThread()
                        .isInterrupted());
                e.printStackTrace();
            }
        }
    }

    static class TaskThread implements Runnable {

        @Override
        public void run() {
            try {
                for (int i = 0; i < 500000; i++) {
                    if (Thread.interrupted()) {
                        System.out.println("线程已经终止， for循环不再执行");
                        throw new InterruptedException();
                    }
                    System.out.println("i=" + (i + 1));
                }

                System.out.println("这是for循环外面的语句，如果不是抛异常而是 break，也会被执行");
            } catch (InterruptedException e) {
                System.out.println("进入MyThread.java类中的catch了。。。");
                e.printStackTrace();
            }
        }
    }
}
