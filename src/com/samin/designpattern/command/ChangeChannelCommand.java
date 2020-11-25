package com.samin.designpattern.command;

public class ChangeChannelCommand implements Command {

    private Television tv;

    public ChangeChannelCommand() {
        tv = new Television();
    }

    @Override
    public void execute(int i) {
        tv.changeChannel(i);
    }
}
