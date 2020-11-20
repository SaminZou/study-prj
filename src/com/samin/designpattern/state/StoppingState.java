package com.samin.designpattern.state;

/** 模拟电梯停止状态 */
public class StoppingState extends BaseState {

    // 模拟电梯的运行方法
    @Override
    public void run() {
        super.mContext.setBaseState(Context.RUNNING_STATE);
        System.out.println("电梯开始跑起来-----------");
    }

    // 模拟电梯的停止方法
    @Override
    public void stop() {
        System.out.println("电梯停止-----------");
    }

    // 模拟电梯的开门方法,中途停止状态省略
    @Override
    public void open() {
        super.mContext.setBaseState(Context.OPENING_STATE);
        System.out.println("电梯开门-----------");
    }

    // 模拟电梯的关门方法
    @Override
    public void close() {
        System.out.println("电梯关门-----------");
    }
}
