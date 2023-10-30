package concurrent.q5;

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
        // stopThread();
        // stopThreadV2();
        // 停止沉睡中的线程
        stopSleepThread();
    }

    static void stopThread() {
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

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(9, 20, 10, TimeUnit.MINUTES, new LinkedBlockingQueue<>(100),
                                                                 r -> new Thread(r, "show-the-error-thread-"
                                                                         + new Random().nextInt(999)));
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
}
