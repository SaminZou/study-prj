package behavioural.state;

/**
 * 模拟电梯关闭状态
 */
public class ClosingState implements BaseState {

    private Context mContext;

    public ClosingState(Context mContext) {
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
        mContext.changeBaseState(new RunningState(this.mContext));
        System.out.println("电梯开始跑起来-----------");
    }

    /**
     * 模拟电梯的停止方法
     */
    @Override
    public void stop() {
        mContext.changeBaseState(new StoppingState(this.mContext));
        System.out.println("电梯关门-----------");
    }

    /**
     * 模拟电梯的开门方法
     */
    @Override
    public void open() {
        mContext.changeBaseState(new StoppingState(this.mContext));
        System.out.println("电梯开门-----------");
    }

    /**
     * 模拟电梯的关门方法
     */
    @Override
    public void close() {
        mContext.changeBaseState(new StoppingState(this.mContext));
        System.out.println("电梯关门-----------");
    }
}
