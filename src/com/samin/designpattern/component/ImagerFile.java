package com.samin.designpattern.component;

public class ImagerFile extends File {

    public ImagerFile(String name) {
        super(name);
    }

    public void display() {
        System.out.println("这是图像文件，文件名：" + super.getName());
    }
}
