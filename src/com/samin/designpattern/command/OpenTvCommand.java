package com.samin.project.command;

public class OpenTvCommand implements Command {
    private Television tv;

    public OpenTvCommand(){
        tv = new Television();
    }

    public void execute(int i) {
        tv.open();
    }
}
