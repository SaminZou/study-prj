package behavioural.state;

/**
 * 模拟电梯打开状态
 */
public class OpeningState implements BaseState {

    private Context mContext;

    public OpeningState(Context mContext) {
        this.mContext = mContext;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 模拟电梯的运行方法
     */
    @Override
    public void run() {
        mContext.changeBaseState(new FaultState(this.mContext));
    }

    /**
     * 模拟电梯的停止方法
     */
    @Override
    public void stop() {
    }

    /**
     * 模拟电梯的开门方法
     */
    @Override
    public void open() {
        System.out.println("电梯开门-----------");
    }

    /**
     * 模拟电梯的关门方法
     */
    @Override
    public void close() {
        mContext.changeBaseState(new ClosingState(this.mContext));
        System.out.println("电梯关门-----------");
    }
}
