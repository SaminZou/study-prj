package com.samin.designpattern.adapter;

public class BioRobot implements Robot {

    public void cry() {
        System.out.println("仿生机器人叫.....");
    }

    public void move() {
        System.out.println("仿生机器人慢慢移动....");
    }
}
