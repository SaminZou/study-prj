package concurrent.q6;

/**
 * 重复调用 start()
 * <p>
 * Description: 重复调用 start()
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2024-07-09
 */
public class ThreadStart {

    public static void main(String[] args) {

        Thread thread = new Thread(new ThreadStates.TestThread());

        // 通常是 NEW
        System.out.println("Before starting thread: " + thread.getState());

        // start() 方法是让线程从 NEW 状态转换为 RUNNABLE
        thread.start();

        // 从非 NEW 状态再次调用 start() 方法抛错 IllegalThreadStateException
        thread.start();
    }

    public static class TestThread implements Runnable {
        @Override
        public void run() {
            try {
                // 让线程进入 TIMED_WAITING 状态
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                Thread.currentThread()
                        .interrupt();
            }
        }
    }
}