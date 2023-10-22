package behavioural.state;

public class Context {

    private BaseState mBaseState;

    public Context(BaseState mBaseState) {
        this.mBaseState = mBaseState;
    }

    public void changeBaseState(BaseState baseState) {
        this.mBaseState = baseState;
        this.mBaseState.setContext(this);
    }

    /**
     * 模拟电梯的运行方法
     *
     * @return
     */
    public Context run() {
        this.mBaseState.run();
        return this;
    }

    /**
     * 模拟电梯的停止方法
     *
     * @return
     */
    public Context stop() {
        this.mBaseState.stop();
        return this;
    }

    /**
     * 模拟电梯的开门方法
     *
     * @return
     */
    public Context open() {
        this.mBaseState.open();
        return this;
    }

    /**
     * 模拟电梯的关门方法
     *
     * @return
     */
    public Context close() {
        this.mBaseState.close();
        return this;
    }
}
