package com.samin.designpattern.bridge;

public class Black implements Color {

    public void paint(String shape) {
        System.out.println("黑色的" + shape);
    }
}
