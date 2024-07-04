package concurrent.q6;

public class ThreadStates2 {

    public static void main(String[] args) {

        Thread thread1 = new Thread(new TestThread());
        Thread thread2 = new Thread(new TestThread());

        thread1.start();
        thread2.start();

        try {
            // 短暂睡眠以尝试捕获状态变化，取决机器性能，等待一下防止线程未变化
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread()
                    .interrupt();
        }

        // 现在线程应该已经执行完毕 BLOCKED
        System.out.println("thread2: " + thread2.getState());

        try {
            // 等待线程执行完毕
            thread1.join();
        } catch (InterruptedException e) {
            Thread.currentThread()
                    .interrupt();
        }

        // 现在线程应该已经执行完毕 TERMINATED
        System.out.println("thread1: " + thread1.getState());
        // 现在线程应该已经执行完毕 RUNNABLE
        System.out.println("thread2: " + thread2.getState());
    }

    public static class TestThread implements Runnable {
        @Override
        public void run() {
            synchronized (TestThread.class) {
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
}