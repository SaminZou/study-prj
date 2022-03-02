package concurrent.q4;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ThreadPoolExecutor 用例
 *
 * @author samin
 * @date 2022-03-02
 */
public class ThreadPoolExecutorUseCase {

    public static void main(String[] args) {
        // 守护线程是 JVM 的内容，操作系统只有守护进程的概念
        // 设置了守护线程，执行结果不会打印到控制台，并且执行完自动关系

        Executor executor = new ThreadPoolExecutor(1, 2, 0, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(2),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());

        // 使用 CallerRunsPolicy 饱和策略时，主线程会被挂起执行任务，等待消费完毕才会继续被唤醒
        for (int i = 1; i < 10; i++) {
            System.out.println("add " + i + " task");
            executor.execute(new TestThread("task " + i));
        }
    }

    private static class TestThread implements Runnable {

        String name;

        public TestThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread：" + Thread.currentThread().getName() + " action：" + name + " done");
        }
    }
}
