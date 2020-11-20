package com.samin.designpattern.singleton;

/** 懒汉模式（线程不安全） */
public class LazySingleton {

    private static LazySingleton singleton = null;

    private LazySingleton() {}

    public static LazySingleton getSingleton() {
        if (singleton == null) {
            singleton = new LazySingleton();
        }
        return singleton;
    }

    // 最简单修改为线程安全的方法，加上同步关键字，效率极其低下，因为这个方法段每次都会执行一次
    // public static synchronized LazySingleton getSingleton() {
    //   if (singleton == null) {
    //       singleton = new LazySingleton();
    //   }
    //   return singleton;
    //  }

    // 注意 singleton 采用 volatile 关键字修饰也是很有必要。
    //
    // singleton 采用 volatile 关键字修饰也是很有必要的，
    // singleton = new Singleton(); 这段代码其实是分为三步执行：
    //
    // 1. 为 singleton 分配内存空间
    // 2. 初始化 singleton
    // 3. 将 singleton 指向分配的内存地址
    //
    // 但是由于 JVM 具有指令重排的特性，执行顺序有可能变成 1->3->2。
    // 指令重排在单线程环境下不会出现问题，但是在多线程环境下会导致一个线程获得还没有初始化的实例。
    // 编译器和处理器通过指令重排可以提高并行效率
    // 例如，线程 T1 执行了 1 和 3，此时 T2 调用 getSingleton() 后发现 singleton 不为空，因此返回
    // singleton，但此时 singleton 还未被初始化。
    //
    // 使用 volatile 可以禁止 JVM 的指令重排，保证在多线程环境下也能正常运行。
}
