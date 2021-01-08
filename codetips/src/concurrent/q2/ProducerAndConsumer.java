package concurrent.q2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 使用 wait 和 notifyAll 实现的消息队列
 *
 * @author samin
 * @date 2021-01-08
 */
public class ProducerAndConsumer {

    /** 控制程序执行时间，单位毫秒 */
    private static final Integer RUNTIME = 100;

    private static Boolean IS_RUNNING = true;

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor poolExecutor =
                new ThreadPoolExecutor(
                        5,
                        10,
                        10,
                        TimeUnit.MINUTES,
                        new LinkedBlockingQueue<>(10),
                        (ThreadFactory) Thread::new);

        Queue<Integer> queue = new LinkedList<>();
        // 数字可以设置大一点，可以观测到无队满或队空的现象，非常巧妙的设计
        int maxSize = 5;

        poolExecutor.execute(new Producer(queue, maxSize));
        poolExecutor.execute(new Consumer(queue));

        Thread.sleep(RUNTIME);
        IS_RUNNING = false;

        poolExecutor.shutdown();
    }

    /** 生产者 */
    private static class Producer implements Runnable {
        private final Queue<Integer> queue;
        private final int maxSize;

        public Producer(Queue<Integer> queue, int maxSize) {
            this.queue = queue;
            this.maxSize = maxSize;
        }

        @Override
        public void run() {
            while (IS_RUNNING) {
                synchronized (queue) {
                    // 满了之后会进行等待
                    while (queue.size() == maxSize) {
                        try {
                            System.out.println("Queue is Full");
                            queue.wait();
                        } catch (InterruptedException ie) {
                            ie.printStackTrace();
                        }
                    }
                    Random random = new Random();
                    int i = random.nextInt();
                    System.out.println("Produce work: " + i);
                    queue.add(i);
                    queue.notifyAll();
                }
            }
        }
    }

    /** 消费者 */
    private static class Consumer implements Runnable {
        private final Queue<Integer> queue;

        public Consumer(Queue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (IS_RUNNING) {
                synchronized (queue) {
                    // 队列空的时候进行等待
                    while (queue.isEmpty()) {
                        System.out.println("Queue is Empty");
                        try {
                            queue.wait();
                        } catch (InterruptedException ie) {
                            ie.printStackTrace();
                        }
                    }
                    int v = queue.remove();
                    System.out.println("Consume work: " + v);
                    queue.notifyAll();
                }
            }
        }
    }
}
