package concurrent.q5;

/**
 * 模拟休眠线程
 *
 * @author samin
 * @date 2023-02-15
 */
public class SleepThread implements Runnable {

    @Override
    public void run() {
        try {
            System.out.println("线程开始。。。");
            Thread.sleep(200000);
            System.out.println("线程结束。");
        } catch (InterruptedException e) {
            System.out.println("在沉睡中被停止, 进入catch， 调用isInterrupted()方法的结果是：" + Thread.currentThread()
                                                                                                     .isInterrupted());
            e.printStackTrace();
        }
    }
}
