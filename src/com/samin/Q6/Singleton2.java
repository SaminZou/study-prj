package com.samin.Q6;

/** 懒汉模式 */
public class Singleton2 {
    private static volatile Singleton2 uniqueInstance;

    private Singleton2() {}

    public static Singleton2 getUniqueInstance() {
        // 先判断对象是否已经实例过，没有实例化过才进入加锁代码
        if (uniqueInstance == null) {
            // 类对象加锁
            synchronized (Singleton2.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Singleton2();
                }
            }
        }
        return uniqueInstance;
    }

    // 注意 uniqueInstance 采用 volatile 关键字修饰也是很有必要。
    //
    // uniqueInstance 采用 volatile 关键字修饰也是很有必要的，
    // uniqueInstance = new Singleton(); 这段代码其实是分为三步执行：
    //
    // 1. 为 uniqueInstance 分配内存空间
    // 2. 初始化 uniqueInstance
    // 3. 将 uniqueInstance 指向分配的内存地址
    //
    // 但是由于 JVM 具有指令重排的特性，执行顺序有可能变成 1->3->2。
    // 指令重排在单线程环境下不会出现问题，但是在多线程环境下会导致一个线程获得还没有初始化的实例。
    // 编译器和处理器通过指令重排可以提高并行效率
    // 例如，线程 T1 执行了 1 和 3，此时 T2 调用 getUniqueInstance() 后发现 uniqueInstance 不为空，因此返回
    // uniqueInstance，但此时 uniqueInstance 还未被初始化。
    //
    // 使用 volatile 可以禁止 JVM 的指令重排，保证在多线程环境下也能正常运行。
}
