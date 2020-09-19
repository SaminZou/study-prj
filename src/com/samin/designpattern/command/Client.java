package com.samin.project.command;

/**
 * 命令模式
 */
public class Client {
    public static void main(String a[]) {
        Command openCommand, closeCommand, changeCommand;

        openCommand = new OpenTvCommand();
        closeCommand = new CloseTvCommand();
        changeCommand = new ChangeChannelCommand();

        Controller control = new Controller(openCommand, closeCommand, changeCommand);

        control.open();           //打开电视机
        control.change();         //换频道
        control.change();
        control.ChannelUndo();
        control.ChannelUndo();
        control.ChannelUndo();
        control.close();          //关闭电视机
    }
}
