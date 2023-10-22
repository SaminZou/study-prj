package behavioural.state;

public interface BaseState {

    void setContext(Context context);

    /**
     * 模拟电梯的运行方法
     */
    void run();

    /**
     * 模拟电梯的停止方法
     */
    void stop();

    /**
     * 模拟电梯的开门方法
     */
    void open();

    /**
     * 模拟电梯的关门方法
     */
    void close();
}
