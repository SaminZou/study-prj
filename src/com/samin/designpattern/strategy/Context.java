package com.samin.project.strategy;

public class Context {
    private Strategy strategy;

    /**
     * 传进的是一个具体的策略实例
     * @param strategy
     */
    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    /**
     * 调用策略
     */
    public void contextInterface() {
        strategy.algorithmLogic();
    }
}
