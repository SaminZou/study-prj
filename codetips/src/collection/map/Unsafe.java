package collection.map;

import java.lang.reflect.Field;

public class Unsafe {

    public static class Person {
        int i = 0;
    }

    private static sun.misc.Unsafe UNSAFE;
    private static long I_OFFSET;

    static {
        // UNSAFE = sun.misc.Unsafe.getUnsafe();
        try {
            Field field = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            UNSAFE = (sun.misc.Unsafe) field.get(null);

            I_OFFSET = UNSAFE.objectFieldOffset(Unsafe.Person.class.getDeclaredField("i"));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Person p = new Person();

        new Thread(() -> {
            while (true) {
                // 线程不安全
                // p.i++;
                // System.out.println(p.i);

                boolean b = UNSAFE.compareAndSwapInt(p, I_OFFSET, p.i, p.i + 1);
                if (b) {
                    System.out.println(UNSAFE.getIntVolatile(p, I_OFFSET));
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
                    System.out.println(UNSAFE.getIntVolatile(p, I_OFFSET));
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