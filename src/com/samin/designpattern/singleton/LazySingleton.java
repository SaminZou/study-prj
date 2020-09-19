package com.samin.designpattern.singleton;

public class LazySingleton {

    private static LazySingleton singleton = null;

    private LazySingleton() {}

    public static synchronized LazySingleton getSingleton() {
        if (singleton == null) {
            singleton = new LazySingleton();
        }
        return singleton;
    }
}
