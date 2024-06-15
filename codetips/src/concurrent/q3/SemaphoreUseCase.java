package concurrent.q3;

import java.util.concurrent.Semaphore;

/**
 * 信号量
 * <p>
 * Description: 信号量
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2024-06-14
 */
public class SemaphoreUseCase {

    private static final int THREAD_COUNT = 5;
    private static final Semaphore semaphore = new Semaphore(2);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < THREAD_COUNT; i++) {
            new Thread(new Worker(i, semaphore)).start();
        }
    }

    static class Worker implements Runnable {
        private final int workerNumber;
        private final Semaphore semaphore;

        Worker(int workerNumber, Semaphore semaphore) {
            this.workerNumber = workerNumber;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("工人 " + workerNumber + " 正在工作");
                // 模拟工作
                Thread.sleep(2000);
                semaphore.release();
            } catch (InterruptedException e) {
                Thread.currentThread()
                        .interrupt();
            }
        }
    }
}