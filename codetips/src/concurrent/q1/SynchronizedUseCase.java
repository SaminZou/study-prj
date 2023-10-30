package concurrent.q1;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Synchronized 用例
 *
 * <p> synchronized关键字用于加锁，但这种锁一是很重，二是获取时必须一直等待，没有额外的尝试机制
 *
 * @author samin
 * @date 2021-01-08
 */
public class SynchronizedUseCase {

    public static int sumsWithLock = 0;
    public static int sumsWithoutLock = 0;

    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 10, 10, TimeUnit.MINUTES, new LinkedBlockingQueue<>(10),
                                                                 (ThreadFactory) Thread::new);

        // 未加锁
        for (int i = 0; i < 5; i++) {
            poolExecutor.execute(() -> {
                for (int j = 0; j < 5000; j++) {
                    sumsWithoutLock += 1;
                }
            });
        }

        // 加锁
        for (int i = 0; i < 5; i++) {
            poolExecutor.execute(() -> {
                for (int j = 0; j < 5000; j++) {
                    // lock
                    synchronized (SynchronizedUseCase.class) {
                        sumsWithLock += 1;
                    }
                }
            });
        }

        // 等待全部线程执行完毕，结果观测才是有意义的，睡眠 5 秒待结果处理完成
        Thread.sleep(5000);
        poolExecutor.shutdown();

        // 注意：未加锁的测试结果可能正确，多执行几次观测结果
        System.out.println("【未加锁模块测试结果】sumsWithoutLock: " + sumsWithoutLock);
        System.out.println("【加锁模块测试结果】sumsUseLock: " + sumsWithLock);
        System.out.println("---------------------------------------");
    }

    /**
     * 加锁实例
     */
    public void method() {
        synchronized (this) {
            System.out.println("synchoronized 代码块");
        }
    }
}
