package behavioural.command;

import behavioural.command.command.ChangeChannelCommand;
import behavioural.command.command.CloseTvCommand;
import behavioural.command.command.Command;
import behavioural.command.command.OpenTvCommand;
import behavioural.command.invoker.ControllerInvoker;
import behavioural.command.receiver.TelevisionReceiver;

/**
 * 命令模式
 *
 * @author samin
 * @date 2021-01-05
 */
public class DemoEntry {

    public static void main(String[] a) {
        // 创建接受者 Receiver
        TelevisionReceiver tv = new TelevisionReceiver();

        // 创建命令 Command
        Command openCommand = new OpenTvCommand(tv);
        Command closeCommand = new CloseTvCommand(tv);
        Command changeCommand = new ChangeChannelCommand(tv);

        // 创建调用者 Invoker
        ControllerInvoker invoker = new ControllerInvoker(openCommand, closeCommand, changeCommand);

        // 实际操作
        invoker.open();
        invoker.change();
        invoker.change();
        invoker.channelUndo();
        invoker.channelUndo();
        invoker.channelUndo();
        invoker.close();
    }
}
