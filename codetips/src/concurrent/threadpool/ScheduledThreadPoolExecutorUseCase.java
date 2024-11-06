package concurrent.threadpool;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 相比 ThreadPoolExecutor，ScheduledThreadPoolExecutor 可以在给定的延迟后运行命令，或者定期执行命令
 *
 * @author samin
 * @date 2022-03-01
 */
public class ScheduledThreadPoolExecutorUseCase {

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
        // 1 秒后开始执行任务，每隔 2 秒执行一次
        executor.scheduleWithFixedDelay(new DelayTask(), 1000, 2000, TimeUnit.MICROSECONDS);
    }

    private static class DelayTask implements Runnable {

        @Override
        public void run() {
            System.out.println("task start ...");

            // doBusiness();

            System.out.println("task finish ...");
        }
    }
}
