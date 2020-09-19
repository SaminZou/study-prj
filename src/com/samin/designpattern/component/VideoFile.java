package com.samin.designpattern.component;

public class VideoFile extends File {

    public VideoFile(String name) {
        super(name);
    }

    public void display() {
        System.out.println("这是影像文件，文件名：" + super.getName());
    }
}
