package concurrent.threadwait;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch await() 方法 timeout 参数使用实例
 *
 * @author samin
 * @date 2021-01-08
 */
public class CountDownLatchTimeoutUseCase {

    public static void main(String[] args) {
        System.out.println("广播体操现在开始... 集合");

        CountDownLatch latch = new CountDownLatch(5);
        User user1 = new User(latch, "张三");
        User user2 = new User(latch, "李四");
        User user3 = new User(latch, "王五");
        User user4 = new User(latch, "小明");
        User user5 = new User(latch, "李雷");

        List<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        list.add(user5);
        for (User ele : list) {
            new Thread(ele).start();
        }

        new Thread(new Remind(latch)).start();
        new Thread(new Alert(latch)).start();
    }

    private static class User implements Runnable {

        CountDownLatch latch;
        private final String userName;

        User(CountDownLatch latch, String name) {
            this.latch = latch;
            this.userName = name;
        }

        @Override
        public void run() {
            System.out.println(userName + " 听到广播，赶往操场...");
            Random random = new Random();
            try {
                Thread.sleep(random.nextInt(5) * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(userName + " 已到操场...");
            latch.countDown();
        }
    }

    private static class Alert implements Runnable {

        CountDownLatch latch;

        Alert(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                // 为了防止部分线程可能出现故障导致主线程永久卡断，一般会设置一个超时时间
                latch.await(3, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("广播体操现在开始...");
            System.out.println("以下同学迟到 ...");
        }
    }

    private static class Remind implements Runnable {

        CountDownLatch latch;

        Remind(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("集合真正完毕...");
        }
    }
}
