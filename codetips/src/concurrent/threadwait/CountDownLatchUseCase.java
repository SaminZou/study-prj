package concurrent.threadwait;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch是JDK提供的一个同步工具， 它可以让一个或多个线程等待，一直等到其他线程中执行完成一组操作
 *
 * <p>常用方法：countDown()：当调用 countDown() 方法时，计数器会被减 1
 * Await：当调用Await方法时，如果计数器大于0时，线程会被阻塞，一直到计数器被 countDown() 方法减到 0 时，线程才会继续执行
 *
 * <p>调用 countDown() 的线程可以继续执行，不需要等待计数器被减到 0 调用 Await 方法的线程需要等待
 *
 * <p>以下用例模拟场景，等客人齐了上菜
 *
 * @author samin
 * @date 2021-01-08
 */
public class CountDownLatchUseCase {

    private static final CountDownLatch latch = new CountDownLatch(3);

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Customer("张三")).start();
        new Thread(new Customer("李四")).start();
        new Thread(new Customer("王五")).start();

        System.out.println("主线程会被卡断");
        // 阻塞主线程
        latch.await();

        new Thread(new Waitress("服务员")).start();
    }

    /**
     * 顾客类
     */
    private static class Customer implements Runnable {

        private final String name;

        public Customer(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
                Random random = new Random();

                System.out.println(sdf.format(new Date()) + " " + name + "出发去饭店");
                Thread.sleep((long) (random.nextDouble() * 3000) + 1000);
                System.out.println(sdf.format(new Date()) + " " + name + "到了饭店");
                // 执行完成
                latch.countDown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 服务员类
     */
    private static class Waitress implements Runnable {

        private final String name;

        public Waitress(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
                System.out.println(sdf.format(new Date()) + " " + name + "开始上菜");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
