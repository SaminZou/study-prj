package com.samin.designpattern.singleton;

/** 静态内部类模式 1. 代码简洁 2. 延迟初始化 3. 线程安全 */
public class InnerStaticSingleton {

    private InnerStaticSingleton() {}

    public static InnerStaticSingleton getSingleton() {
        return Inner.instance;
    }

    private static class Inner {
        private static final InnerStaticSingleton instance = new InnerStaticSingleton();
    }
}
