package com.samin.designpattern.bridge;

public class Gray implements Color {

    public void paint(String shape) {
        System.out.println("灰色的" + shape);
    }
}
