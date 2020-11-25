package com.samin.designpattern.bridge;

public class Circle extends Shape {

    @Override
    public void draw() {
        color.paint("正方形");
    }
}
