package com.samin.designpattern.singleton;

public class EagerSingleton {

    private static EagerSingleton singleton = new EagerSingleton();

    private EagerSingleton() {}

    public static EagerSingleton getSingleton() {
        return singleton;
    }
}
