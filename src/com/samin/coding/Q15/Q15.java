package com.samin.coding.Q15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/** 使用wait/notify */
public class Q15 {
    private static class Queue {
        List<Integer> list;
        int nums = 1;

        Queue() {
            list = new ArrayList<>();
        }

        public void add() {
            list.add(nums++);
        }
    }

    private static class ThreadA implements Runnable {
        final Object lock;
        Queue queue;

        ThreadA(Object lock, Queue queue) {
            this.lock = lock;
            this.queue = queue;
        }

        @Override
        public void run() {
            synchronized (lock) {
                if (queue.nums != 5) {
                    System.out.println("启动A线程，开始等待");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    queue.add();
                }
                System.out.println("A执行完毕!nums:" + queue.nums);
            }
        }
    }

    private static class ThreadB implements Runnable {
        final Object lock;
        Queue queue;

        ThreadB(Object lock, Queue queue) {
            this.lock = lock;
            this.queue = queue;
        }

        @Override
        public void run() {
            synchronized (lock) {
                while (queue.nums < 5) {
                    System.out.println("启动线程B");
                    queue.add();
                    lock.notify();
                }
                System.out.println("B执行完毕!nums:" + queue.nums);
            }
        }
    }

    public static void main(String[] args) {
        HashMap<String, String> newMap = new HashMap<>();
        System.out.println(newMap);
        Object lock = new Object();
        Queue queue = new Queue();
        Thread threadA = new Thread(new ThreadA(lock, queue));
        Thread threaB = new Thread(new ThreadB(lock, queue));
        threadA.start();
        threaB.start();
    }
}
