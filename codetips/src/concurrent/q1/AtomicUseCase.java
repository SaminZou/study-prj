package concurrent.q1;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 对比了在并发场景中，原子类型和原生类型的计算结果，原子类型符合预期结果
 *
 * @author samin
 * @date 2020-12-30
 */
public class AtomicUseCase {

    /**
     * 使用了原子类型
     */
    public static AtomicInteger sumsAtomicInteger = new AtomicInteger(0);
    /**
     * 没有使用原子类型
     */
    public static Integer sumsInteger = 0;

    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 10, 10, TimeUnit.MINUTES, new LinkedBlockingQueue<>(10),
                r -> new Thread(r, "show-the-fixed-thread-" + new Random().nextInt(999)));

        for (int i = 0; i < 10; i++) {
            poolExecutor.execute(new AtomicMockThread());
        }

        // 等待全部线程执行完毕，结果观测才是有意义的
        Thread.sleep(5000);

        System.out.println("sumsAtomicInteger: " + sumsAtomicInteger);

        System.out.println("----------------------------------------------------------");

        for (int i = 0; i < 10; i++) {
            poolExecutor.execute(new IntegerMockThread());
        }

        // 等待全部线程执行完毕，结果观测才是有意义的
        Thread.sleep(5000);

        System.out.println("sumsInteger: " + sumsInteger);

        poolExecutor.shutdown();
    }

    static class AtomicMockThread implements Runnable {

        @Override
        public void run() {
            for (int j = 0; j < 5000; j++) {
                // 用原子类操作数据，也可以保证线程安全
                sumsAtomicInteger.addAndGet(1);
            }
        }
    }

    static class IntegerMockThread implements Runnable {

        @Override
        public void run() {
            for (int j = 0; j < 5000; j++) {
                sumsInteger += 1;
            }
        }
    }
}
