package behavioural.state;

/**
 * 没有使用状态模式
 */
public class NoState {

    public static final int CLOSING_STATE = 0;
    public static final int FAULT_STATE = 1;
    public static final int OPENING_STATE = 2;
    public static final int RUNNING_STATE = 3;
    public static final int STOPPING_STATE = 4;

    private static int state;

    // 模拟电梯的运行方法
    public void run() {
        switch (state) {
            case CLOSING_STATE:
                //              执行具体的方法
                //              behavioural.state = XXXX;
                break;
            case FAULT_STATE:
                //              执行具体的方法
                //              behavioural.state = XXXX;
                break;
            case OPENING_STATE:
                //              执行具体的方法
                //              behavioural.state = XXXX;
                break;
            case RUNNING_STATE:
                //              执行具体的方法
                //              behavioural.state = XXXX;
                break;
            case STOPPING_STATE:
                //              执行具体的方法
                //              behavioural.state = XXXX;
                break;
            default:
                break;
        }
    }

    // 模拟电梯的停止方法
    public void stop() {
        switch (state) {
            case CLOSING_STATE:
                //              执行具体的方法
                //              behavioural.state = XXXX;
                break;
            case FAULT_STATE:
                //              执行具体的方法
                //              behavioural.state = XXXX;
                break;
            case OPENING_STATE:
                //              执行具体的方法
                //              behavioural.state = XXXX;
                break;
            case RUNNING_STATE:
                //              执行具体的方法
                //              behavioural.state = XXXX;
                break;
            case STOPPING_STATE:
                //              执行具体的方法
                //              behavioural.state = XXXX;
                break;
            default:
                break;
        }
    }

    // 模拟电梯的开门方法
    public void open() {
        switch (state) {
            case CLOSING_STATE:
                //              执行具体的方法
                //              behavioural.state = XXXX;
                break;
            case FAULT_STATE:
                //              执行具体的方法
                //              behavioural.state = XXXX;
                break;
            case OPENING_STATE:
                //              执行具体的方法
                //              behavioural.state = XXXX;
                break;
            case RUNNING_STATE:
                //              执行具体的方法
                //              behavioural.state = XXXX;
                break;
            case STOPPING_STATE:
                //              执行具体的方法
                //              behavioural.state = XXXX;
                break;
            default:
                break;
        }
    }

    // 模拟电梯的关门方法
    public void close() {
        switch (state) {
            case CLOSING_STATE:
                //              执行具体的方法
                //              behavioural.state = XXXX;
                break;
            case FAULT_STATE:
                //              执行具体的方法
                //              behavioural.state = XXXX;
                break;
            case OPENING_STATE:
                //              执行具体的方法
                //              behavioural.state = XXXX;
                break;
            case RUNNING_STATE:
                //              执行具体的方法
                //              behavioural.state = XXXX;
                break;
            case STOPPING_STATE:
                //              执行具体的方法
                //              behavioural.state = XXXX;
                break;
            default:
                break;
        }
    }
}
