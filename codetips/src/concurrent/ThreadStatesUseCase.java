package concurrent;

public class ThreadStatesUseCase {

    public static void main(String[] args) {

        Thread thread = new Thread(new TestThread());

        // 通常是 NEW
        System.out.println("Before starting thread: " + thread.getState());

        thread.start();

        try {
            // 短暂睡眠以尝试捕获状态变化，取决机器性能，等待一下防止线程未变化
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread()
                    .interrupt();
        }

        // 注意：这里获取的状态可能仍然是 RUNNABLE 或 TIMED_WAITING，取决于执行时机
        System.out.println("After starting thread (quickly checked): " + thread.getState());

        try {
            // 等待线程执行完毕
            thread.join();
        } catch (InterruptedException e) {
            Thread.currentThread()
                    .interrupt();
        }

        // 现在线程应该已经执行完毕 TERMINATED
        System.out.println("After thread has finished: " + thread.getState());
    }

    public static class TestThread implements Runnable {
        @Override
        public void run() {
            try {
                // 让线程进入 TIMED_WAITING 状态
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread()
                        .interrupt();
            }
            // 线程执行完毕，将自动变为 TERMINATED 状态
        }
    }
}