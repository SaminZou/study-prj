package com.samin.designpattern.singleton;

/** 饿汉模式（类加载初始化的时候执行，浪费内存） */
public class EagerSingleton {

    private static EagerSingleton singleton = new EagerSingleton();

    private EagerSingleton() {}

    public static EagerSingleton getSingleton() {
        return singleton;
    }
}
