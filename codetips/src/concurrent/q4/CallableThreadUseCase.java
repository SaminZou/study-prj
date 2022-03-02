package concurrent.q4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Future 用例
 *
 * @author samin
 * @date 2021-01-08
 */
public class CallableThreadUseCase {

    static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 这种使用是无意义的
        Callable<String> task = new CallableWorker(1, 2);
        FutureTask<String> ft = new FutureTask<>(task);
        new Thread(ft).start();
        // 阻塞
        System.out.println(ft.get());

        // 线程池，注意阻塞队列的容量配置，如果不指定数量，会一直增长，线程池将一直使用 corePoolSize
        ExecutorService threadPool =
                new ThreadPoolExecutor(
                        10,
                        50,
                        10,
                        TimeUnit.SECONDS,
                        new LinkedBlockingDeque<>(10),
                        (ThreadFactory) Thread::new);

        System.out.println("-------------------------------------------------------------------");

        // 借助列表完成循环遍历先完成的线程，只有当异步执行完成，才进行线程阻塞返回结果
        List<Future<String>> resList = new ArrayList<>();
        for (int i = 0; i < 100; i = i + 2) {
            Integer x = i;
            Integer y = i + 1;
            Future<String> res = threadPool.submit(new CallableWorker(x, y));
            resList.add(res);
        }

        while (!resList.isEmpty()) {
            Iterator<Future<String>> iterator = resList.iterator();
            while (iterator.hasNext()) {
                Future<String> future = iterator.next();
                if (future.isDone()) {
                    System.out.println(future.get());
                    iterator.remove();
                }
            }
        }

        threadPool.shutdown();
    }

    /** 模拟计算 50 个计算任务 */
    private static class CallableWorker implements Callable<String> {
        private final Integer x;
        private final Integer y;

        public CallableWorker(Integer x, Integer y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String call() throws Exception {
            Thread.sleep(new Random().nextInt(5) * 100);
            int seq = atomicInteger.getAndAdd(1);
            return "seq:" + seq + " ,x:" + x + " ,y:" + y + " ,sum:" + (x + y);
        }
    }
}
