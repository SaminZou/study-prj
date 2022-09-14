package concurrent.q1;

/**
 * Volatile 用例
 *
 * @author samin
 * @date 2022-04-19
 */
public class VolatileUseCase extends Thread {

    // 这里需要加上 volatile
    // 可见性原理
    private volatile boolean stopFlag = false;

    public boolean isStopFlag() {
        return stopFlag;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        stopFlag = true;
        System.out.println(Thread.currentThread().getName() + " stopFlag = " + stopFlag);
    }

    public static void main(String[] args) {
        VolatileUseCase vt = new VolatileUseCase();
        vt.start();

        while (true) {
            if (vt.isStopFlag()) {
                System.out.println("stop");
                break;
            }
        }
    }
}
