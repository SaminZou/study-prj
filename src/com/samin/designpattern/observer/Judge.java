package com.samin.designpattern.observer;

public class Judge extends Subject {

    public void instruction() {
        System.out.println("123,木头人！");
        // 通知所有观察者
        notifyObserver();
    }
}
