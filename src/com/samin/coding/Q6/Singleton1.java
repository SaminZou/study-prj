package com.samin.coding.Q6;

/** 饿汉模式 */
public class Singleton1 {
    // 利用静态变量来记录Singleton的唯一实例，确保线程安全
    private static Singleton1 uniqueInstance = new Singleton1();

    /** 私有化，只有Singleton类内才可以调用构造器 */
    private Singleton1() {}

    public static Singleton1 getInstance() {
        return uniqueInstance;
    }
}
