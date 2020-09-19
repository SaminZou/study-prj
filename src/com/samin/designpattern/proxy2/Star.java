package com.samin.project.proxy2;

public class Star implements Subject {
    @Override
    public void movie() {
        System.out.println("I am a star. start working...");
    }

    @Override
    public Agent getAgent() {
        return new Agent(this);
    }
}
