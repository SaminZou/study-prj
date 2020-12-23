package com.samin.designpattern.command;

public class OpenTvCommand implements Command {

    private final Television tv;

    public OpenTvCommand() {
        tv = new Television();
    }

    @Override
    public void execute(int i) {
        tv.open();
    }
}
