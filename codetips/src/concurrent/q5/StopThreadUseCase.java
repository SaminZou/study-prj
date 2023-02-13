package concurrent.q5;

/**
 * 停止一个正在运行的线程
 *
 * @author samin
 * @date 2023-02-13
 */
public class StopThreadUseCase {

    public static void main(String[] args) {
        // 停止正在运行的线程
        // stopThread();
        // 停止沉睡中的线程
        stopSleepThread();
    }

    static void stopThread() {
        TaskThread taskThread = new TaskThread();
        Thread thread = new Thread(taskThread, "taskThread");
        thread.start();
        try {
            Thread.sleep(100);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void stopSleepThread() {
        SleepThread sleepThread = new SleepThread();
        Thread thread = new Thread(sleepThread, "sleepThread");
        thread.start();
        thread.interrupt();
    }
}
