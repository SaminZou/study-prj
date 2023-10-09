package behavioural.command;

/**
 * 命令模式
 *
 * @author samin
 * @date 2021-01-05
 */
public class Client {

    public static void main(String[] a) {
        // 创建接受者 Receiver
        Television tv = new Television();

        // 创建命令 Command
        Command openCommand = new OpenTvCommand(tv);
        Command closeCommand = new CloseTvCommand(tv);
        Command changeCommand = new ChangeChannelCommand(tv);

        // 创建调用者 Invoker
        Controller control = new Controller(openCommand, closeCommand, changeCommand);

        // 实际操作
        control.open();
        control.change();
        control.change();
        control.channelUndo();
        control.channelUndo();
        control.channelUndo();
        control.close();
    }
}
