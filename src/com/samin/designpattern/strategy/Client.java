package com.samin.designpattern.strategy;

/**
 * 策略模式
 *
 * 通过多态，让使用者感知不到内部策略的变化，策略方法本身是主体
 */
public class Client {

    public static void main(String[] args) {
        // 操控比赛，这场要输
        Context context = new Context(new ConcreteStrategyB());
        context.contextInterface();
    }
}
