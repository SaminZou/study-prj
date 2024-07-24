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
        // v1();
        v2();
    }

    public static void v1() throws ExecutionException, InterruptedException {
        // 线程池，注意阻塞队列的容量配置，如果不指定数量，会一直增长，线程池将一直使用 corePoolSize
        ExecutorService threadPool = new ThreadPoolExecutor(10, 50, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>(10), (ThreadFactory) Thread::new);

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

    public static void v2() throws ExecutionException, InterruptedException {
        ExecutorService threadPool = new ThreadPoolExecutor(10, 50, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>(10), (ThreadFactory) Thread::new);

        System.out.println("-------------------------------------------------------------------");

        // 借助列表完成循环遍历先完成的线程，只有当异步执行完成，才进行线程阻塞返回结果
        List<CompletableFuture<String>> futures = new ArrayList<>();
        for (int i = 0; i < 100; i = i + 2) {
            Integer x = i;
            Integer y = i + 1;
            CompletableFuture<String> res = CompletableFuture.supplyAsync(() -> {
                try {
                    return new CallableWorker(x, y).call();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }, threadPool);
            futures.add(res);
        }

        // 使用 CompletableFuture.allOf 等待所有任务完成
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        // 阻塞直到所有任务完成
        allFutures.join();

        for (CompletableFuture<String> future : futures) {
            System.out.println(future.join());
        }

        threadPool.shutdown();
    }

    /**
     * 模拟计算 50 个计算任务
     */
    private static class CallableWorker implements Callable<String> {

        private final Integer x;
        private final Integer y;

        public CallableWorker(Integer x, Integer y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String call() throws Exception {
            // 随机休眠 100~500 毫秒
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(401) + 100);
            int seq = atomicInteger.getAndAdd(1);
            return "seq:" + seq + " ,x:" + x + " ,y:" + y + " ,sum:" + (x + y);
        }
    }
}
