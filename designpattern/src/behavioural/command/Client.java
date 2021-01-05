package behavioural.command;

/**
 * 命令模式
 *
 * @author samin
 * @date 2021-01-05
 */
public class Client {

    public static void main(String[] a) {
        Command openCommand, closeCommand, changeCommand;

        openCommand = new OpenTvCommand();
        closeCommand = new CloseTvCommand();
        changeCommand = new ChangeChannelCommand();

        Controller control = new Controller(openCommand, closeCommand, changeCommand);

        // 打开电视机
        control.open();
        // 换频道
        control.change();
        control.change();
        control.ChannelUndo();
        control.ChannelUndo();
        control.ChannelUndo();
        // 关闭电视机
        control.close();
    }
}
