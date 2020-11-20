package com.samin.coding.Q17;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch是JDK提供的一个同步工具， 它可以让一个或多个线程等待，一直等到其他线程中执行完成一组操作
 *
 * <p>常用方法： CountDown：当调用CountDown方法时，计数器会被减1
 * Await：当调用Await方法时，如果计数器大于0时，线程会被阻塞，一直到计数器被CountDown方法减到0时，线程才会继续执行
 *
 * <p>调用CountDown的线程可以继续执行，不需要等待计数器被减到0 调用Await方法的线程需要等待
 *
 * <p>以下用例模拟场景，等客人齐了上菜
 */
public class CountDownLatchUseCase {

    // 顾客类
    private static class Customer implements Runnable {
        private CountDownLatch latch;
        private String name;

        public Customer(CountDownLatch latch, String name) {
            this.latch = latch;
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
                latch.countDown(); // 执行完成
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 服务员类
    private static class Waitress implements Runnable {
        private CountDownLatch latch;
        private String name;

        public Waitress(CountDownLatch latch, String name) {
            this.latch = latch;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
                System.out.println(sdf.format(new Date()) + " " + name + "等待顾客");
                latch.await();
                System.out.println(sdf.format(new Date()) + " " + name + "开始上菜");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);

        new Thread(new Customer(latch, "张三")).start();
        new Thread(new Customer(latch, "李四")).start();
        new Thread(new Customer(latch, "王五")).start();

        Thread.sleep(100);
        new Thread(new Waitress(latch, "♥小芳♥")).start();
    }
}
