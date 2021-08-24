package concurrent.q3;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier 调用await()是在任务线程调用的
 *
 * <p>CyclicBarrier(int parties)int类型的参数表示有几个线程来参与这个屏障拦截
 *
 * <p>CyclicBarrier(int parties,Runnable barrierAction)当所有线程到达一个屏障点时，优先执行barrierAction这个线程。
 *
 * @author samin
 * @date 2021-08-24
 */
public class CyclicBarrierUseCase {

    private static final CyclicBarrier latch = new CyclicBarrier(3);

    public static void main(String[] args) throws InterruptedException {
        new Thread(new CyclicBarrierUseCase.Customer("张三")).start();
        new Thread(new CyclicBarrierUseCase.Customer("李四")).start();
        new Thread(new CyclicBarrierUseCase.Customer("王五")).start();
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
                latch.await(); // 执行完成
                System.out.println(sdf.format(new Date()) + " " + "开始上菜");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
