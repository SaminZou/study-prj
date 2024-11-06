package concurrent.waitnotify;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 使用 wait / notify
 *
 * @author samin
 * @date 2021-01-08
 */
public class WaitAndNotifyUseCase {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 10, TimeUnit.MINUTES, new LinkedBlockingQueue<>(10),
                                                             r -> new Thread(r,
                                                                             "show-the-fixed-thread-" + new Random().nextInt(
                                                                                     999)));

        Queue queue = new Queue();
        executor.execute(new ThreadA(queue));
        executor.execute(new ThreadB(queue));

        executor.shutdown();
    }

    private static class Queue {

        int nums = 0;

        Queue() {
        }

        // 模拟操作方法，打印执行线程
        public void add() {
            nums++;
            System.out.println(Thread.currentThread()
                                     .getName() + " is done");
        }
    }

    /**
     * 等待信号后执行
     */
    private static class ThreadA implements Runnable {

        final Queue queue;

        ThreadA(Queue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            synchronized (queue) {
                System.out.println(Thread.currentThread()
                                         .getName() + " is running , wait for lock");
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queue.add();
            }
        }
    }

    /**
     * 执行方法，注意是执行完整个方法，不管耗时多久，不管 `notify()` 方法在 `synchronized` 哪一步，都是等待线程执行完毕才发送信号
     */
    private static class ThreadB implements Runnable {

        final Queue queue;

        ThreadB(Queue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            synchronized (queue) {
                queue.notify();

                while (queue.nums < 5) {
                    queue.add();
                }
            }
        }
    }
}
