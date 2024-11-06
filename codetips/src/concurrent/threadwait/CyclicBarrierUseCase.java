package concurrent.threadwait;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier 调用 await() 是在任务线程调用的
 *
 * <p>CyclicBarrier(int parties) int 类型的参数表示有几个线程来参与这个屏障拦截
 *
 * <p>CyclicBarrier(int parties, Runnable barrierAction) 当所有线程到达一个屏障点时，优先执行 barrierAction 这个线程。
 *
 * @author samin
 * @date 2021-08-24
 */
public class CyclicBarrierUseCase {

    private static final CyclicBarrier latch = new CyclicBarrier(3, new Waitress("服务员"));

    public static void main(String[] args) throws InterruptedException {
        new Thread(new CyclicBarrierUseCase.Customer("张三")).start();
        new Thread(new CyclicBarrierUseCase.Customer("李四")).start();
        new Thread(new CyclicBarrierUseCase.Customer("王五")).start();

        System.out.println("主线程不会卡断");
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
                latch.await();
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
