package com.samin.coding.Q23;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TPEUseCase {
    static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Callable 接口任务 需要 FutureTask，本方式和普通的代码执行方式一样，不能体现出线程异步方式
        Callable<String> task = new CallableWorker(1, 2);
        FutureTask<String> ft = new FutureTask<>(task);
        new Thread(ft).start();
        System.out.println(ft.get());

        // 线程池，注意阻塞队列的容量配置，如果不指定数量，会一直增长，线程池将一直使用corePoolSize
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

    // 模拟计算50个计算任务
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
