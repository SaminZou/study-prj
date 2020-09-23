package com.samin.Q13;

import java.text.SimpleDateFormat;
import java.util.Random;

public class ThreadLocalUseCase implements Runnable {
    // SimpleDateFormat 不是线程安全的，所以每个线程都要有自己独立的副本
    private static final ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMdd HHmm"));
    private static final ThreadLocal<String> str = ThreadLocal.withInitial(() -> "test");

    // 每个线程都会修改formatter，不影响其他线程
    @Override
    public void run() {
        System.out.println("Thread Name= " + Thread.currentThread().getName() + " default Formatter = " + formatter.get().toPattern());
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //formatter pattern is changed here by thread, but it won't reflect to other threads
        formatter.set(new SimpleDateFormat());

        System.out.println("Thread Name= " + Thread.currentThread().getName() + " formatter = " + formatter.get().toPattern());
        formatter.remove(); // 避免内存泄露
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadLocalUseCase obj = new ThreadLocalUseCase();
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(obj, "" + i);
            Thread.sleep(new Random().nextInt(1000));
            t.start();
        }
    }
}
