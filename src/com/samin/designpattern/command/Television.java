package com.samin.project.command;

public class Television {
    public void open() {
        System.out.println("打开电视机......");
    }

    public void close() {
        System.out.println("关闭电视机......");
    }

    public void changeChannel(int i) {
        System.out.println("切换电视频道......现在的频道是：" + i);
    }
}
