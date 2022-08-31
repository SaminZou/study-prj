package behavioural.state;

/**
 * 模拟电梯运行状态
 */
public class RunningState extends BaseState {

    // 模拟电梯的运行方法
    @Override
    public void run() {
        System.out.println("电梯开始跑起来-----------");
    }

    // 模拟电梯的停止方法
    @Override
    public void stop() {
        super.mContext.setBaseState(Context.STOPPING_STATE);
        System.out.println("电梯停止-----------");
    }

    // 模拟电梯的开门方法
    @Override
    public void open() {
        super.mContext.setBaseState(Context.FAULT_STATE);
        System.out.println("电梯发生故障");
    }

    // 模拟电梯的关门方法
    @Override
    public void close() {
        System.out.println("电梯关门-----------");
    }
}
