package behavioural.state;

/**
 * 状态模式
 *
 * @author samin
 * @date 2021-01-05
 */
public class DemoEntry {

    public static void main(String[] args) {
        Context context = new Context(new NoState());
        context.run()
               .open()
               .close();

        context.changeBaseState(new ClosingState(context));
        context.stop()
               .open()
               .run()
               .open();

        context.changeBaseState(new RunningState(context));
        context.stop()
               .open()
               .run()
               .open();
    }
}
