package com.samin.project.command;

public class ChangeChannelCommand implements Command {
    private Television tv;

    public ChangeChannelCommand() {
        tv = new Television();
    }

    public void execute(int i) {
        tv.changeChannel(i);
    }
}
