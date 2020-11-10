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
}
