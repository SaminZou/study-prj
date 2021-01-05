package concurrent.q2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/** 使用wait和notifyAll实现的消息队列 */
public class ProducerAndConsumer {

    // 控制程序执行时间，单位毫秒
    private static final Integer RUNTIME = 100;
    private static Boolean IS_RUNNING = true;

    public static void main(String[] args) throws InterruptedException {
        Queue<Integer> queue = new LinkedList<>();
        // 数字可以设置大一点，可以观测到无队满或队空的现象，非常巧妙的设计
        int maxSize = 5;

        new Thread(new Producer(queue, maxSize)).start();
        new Thread(new Consumer(queue)).start();

        Thread.sleep(RUNTIME);
        IS_RUNNING = false;
    }

    // 生产者
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

    // 消费者
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
