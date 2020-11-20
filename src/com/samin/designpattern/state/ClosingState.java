package com.samin.designpattern.state;

/** 模拟电梯关闭状态 */
public class ClosingState extends BaseState {

    // 模拟电梯的运行方法
    @Override
    public void run() {
        super.mContext.setBaseState(Context.RUNNING_STATE);
        System.out.println("电梯开始跑起来-----------");
    }

    // 模拟电梯的停止方法
    @Override
    public void stop() {
        super.mContext.setBaseState(Context.STOPPING_STATE);
        System.out.println("电梯关门-----------");
    }

    // 模拟电梯的开门方法
    @Override
    public void open() {
        super.mContext.setBaseState(Context.CLOSING_STATE);
        System.out.println("电梯开门-----------");
    }

    // 模拟电梯的关门方法
    @Override
    public void close() {
        super.mContext.setBaseState(Context.STOPPING_STATE);
        System.out.println("电梯关门-----------");
    }
}
