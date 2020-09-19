package com.samin.project.strategy;

/*
* 策略模式
* */
public class Client {
    public static void main(String[] args) {
        // 操控比赛，这场要输
        Context context = new Context(new ConcreteStrategyB());
        context.contextInterface();
    }
}
