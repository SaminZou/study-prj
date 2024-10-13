package collection;

import java.lang.reflect.Field;
import java.util.concurrent.locks.ReentrantLock;

public class UnsafeUseCase {

    public static class Person {
        private int i = 0;
        private String[] table = {"1", "2", "3", "4", "5"};
    }

    private static sun.misc.Unsafe UNSAFE;
    private static long I_OFFSET;

    static {
        // UNSAFE = sun.misc.Unsafe.getUnsafe();
        try {
            Field field = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            UNSAFE = (sun.misc.Unsafe) field.get(null);

            I_OFFSET = UNSAFE.objectFieldOffset(UnsafeUseCase.Person.class.getDeclaredField("i"));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        // case1();

        // case2();

        case3();
    }

    /**
     * ReentrantLock lock() 和 tryLock() 的区别
     */
    public static void case3() {
        ReentrantLock lock = new ReentrantLock();

        new Thread(() -> {
            lock.lock();

            System.out.println("线程 1 正在执行 ...");

            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            lock.unlock();
        }).start();

        new Thread(() -> {
            // 阻塞无法做其他事情
            // lock.lock();

            while (!lock.tryLock()){
                System.out.println("线程 2 获取锁的过程中做其他事情");

                try {
                    Thread.sleep(1 * 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                lock.lock();
            }

            System.out.println("线程 2 正在执行 ...");

            lock.unlock();
        }).start();
    }

    /**
     * 模拟并发情况下安全获取数据
     */
    public static void case2() {
        Person p = new Person();

        // 数组中存储的对象的对象头大小
        int ns = UNSAFE.arrayIndexScale(String[].class);
        // 数组中第一个元素的起始位置
        int base = UNSAFE.arrayBaseOffset(String[].class);
        System.out.println(UNSAFE.getObject(p.table, base + 1 * ns));
    }

    /**
     * 演示 Unsafe 类的 CAS 机制
     * <p>
     * 此演示可能会出现打印相同数字的情况，原因是打印的时候可能出现 CPU 调度，导致打印出最新结果，导致观察出现重复数字
     */
    public static void case1() {
        Person p = new Person();

        new Thread(() -> {
            while (true) {
                // 线程不安全
                // p.i++;
                // System.out.println(p.i);

                boolean b = UNSAFE.compareAndSwapInt(p, I_OFFSET, p.i, p.i + 1);
                if (b) {
                    System.out.println(Thread.currentThread()
                            .getName() + ": " + UNSAFE.getIntVolatile(p, I_OFFSET));
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                // 线程不安全
                // p.i++;
                // System.out.println(p.i);

                boolean b = UNSAFE.compareAndSwapInt(p, I_OFFSET, p.i, p.i + 1);
                if (b) {
                    System.out.println(Thread.currentThread()
                            .getName() + ": " + UNSAFE.getIntVolatile(p, I_OFFSET));
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}