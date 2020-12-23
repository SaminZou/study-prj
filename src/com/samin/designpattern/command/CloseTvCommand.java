package com.samin.designpattern.command;

public class CloseTvCommand implements Command {

    private final Television tv;

    public CloseTvCommand() {
        tv = new Television();
    }

    @Override
    public void execute(int i) {
        tv.close();
    }
}
