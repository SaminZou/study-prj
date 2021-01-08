package concurrent.q1;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Synchronized 用例
 *
 * @author samin
 * @date 2021-01-08
 */
public class SynchronizedUseCase {
    public static int sums = 0;

    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor poolExecutor =
                new ThreadPoolExecutor(
                        5,
                        10,
                        10,
                        TimeUnit.MINUTES,
                        new LinkedBlockingQueue<>(10),
                        (ThreadFactory) Thread::new);

        for (int i = 0; i < 5; i++) {
            poolExecutor.execute(
                    () -> {
                        for (int j = 0; j < 5000; j++) {
                            // 加锁控制
                            synchronized (SynchronizedUseCase.class) {
                                sums += 1;
                            }
                        }
                    });
        }

        // 等待全部线程执行完毕，结果观测才是有意义的
        Thread.sleep(5000);
        poolExecutor.shutdown();

        System.out.println("sums: " + sums);
        System.out.println("---------------------------------------");
    }

    public void method() {
        synchronized (this) {
            System.out.println("synchoronized 代码块");
        }
    }
}
