package behavioural.state;

/**
 * 状态模式
 *
 * @author samin
 * @date 2021-01-05
 */
public class Client {

    public static void main(String[] args) {
        Context context = new Context();
        context.setBaseState(new ClosingState());
        context.run().open().close();

        Context context1 = new Context();
        context1.setBaseState(new RunningState());
        context1.stop().open().run().open();
    }
}
