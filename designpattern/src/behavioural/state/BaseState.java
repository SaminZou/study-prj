package behavioural.state;

public abstract class BaseState {

    protected Context mContext;

    public void setContext(Context context) {
        mContext = context;
    }

    // 模拟电梯的运行方法
    public abstract void run();

    // 模拟电梯的停止方法
    public abstract void stop();

    // 模拟电梯的开门方法
    public abstract void open();

    // 模拟电梯的关门方法
    public abstract void close();
}
