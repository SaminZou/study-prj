package concurrent.q1;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 用例
 *
 * <p> 使用 ReentrantLock 比直接使用 synchronized 更安全，线程在 tryLock() 失败的时候不会导致死锁。
 *
 * <p> ReentrantLock 可以替代 synchronized 进行同步；
 *
 * <p> ReentrantLock 获取锁更安全；
 *
 * <p> 必须先获取到锁，再进入 try {...} 代码块，最后使用 finally 保证释放锁；
 *
 * <p> 可以使用 tryLock() 尝试获取锁。
 *
 * @author samin
 * @date 2021-01-08
 */
public class ReentrantLockUseCase {

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
                final Lock lock = new ReentrantLock();
                for (int j = 0; j < 5000; j++) {
                    // lock
                    lock.lock();
                    try {
                        // 在尝试获取锁的时候，最多等待 1 秒。如果 1 秒后仍未获取到锁，tryLock() 返回 false，程序就可以做一些额外处理，而不是无限等待下去
                        if (lock.tryLock(1, TimeUnit.SECONDS)) {
                            try {
                                sumsWithLock += 1;
                            } finally {
                                lock.unlock();
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
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
}
