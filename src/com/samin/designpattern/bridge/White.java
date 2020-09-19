package com.samin.designpattern.bridge;

public class White implements Color {

    public void paint(String shape) {
        System.out.println("白色的" + shape);
    }
}
