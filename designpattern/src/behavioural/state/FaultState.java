package behavioural.state;

/**
 * 模拟故障状态,四个方法都不可用
 */
public class FaultState implements BaseState {

    private Context mContext;

    public FaultState(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 模拟电梯的运行方法
     */
    @Override
    public void run() {
        System.out.println("电梯发生故障，不能正常工作");
    }

    /**
     * 模拟电梯的停止方法
     */
    @Override
    public void stop() {
        System.out.println("电梯发生故障，不能正常工作");
    }

    /**
     * 模拟电梯的开门方法
     */
    @Override
    public void open() {
        System.out.println("电梯发生故障，不能正常工作");
    }

    /**
     * 模拟电梯的关门方法
     */
    @Override
    public void close() {
        System.out.println("电梯发生故障，不能正常工作");
    }
}
