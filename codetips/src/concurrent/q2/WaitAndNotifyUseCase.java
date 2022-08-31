package concurrent.q2;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用wait/notify
 *
 * @author samin
 * @date 2021-01-08
 */
public class WaitAndNotifyUseCase {

    public static void main(String[] args) {
        Queue queue = new Queue();
        Thread threadA = new Thread(new ThreadA(queue), "Thread A");
        Thread threadB = new Thread(new ThreadB(queue), "Thread B");
        threadA.start();
        threadB.start();
    }

    private static class Queue {

        List<Integer> list;
        int nums = 1;

        Queue() {
            list = new ArrayList<>();
        }

        // 模拟操作方法，打印执行线程
        public void add() {
            list.add(nums++);
            System.out.println(Thread.currentThread().getName() + " is done");
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
                System.out.println(Thread.currentThread().getName() + " is running , wait for lock");
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
