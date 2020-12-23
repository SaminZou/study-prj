package com.samin.designpattern.flyweight;

public class Circle extends Shape {

    private final String color;

    public Circle(String color) {
        this.color = color;
    }

    @Override
    public void draw() {
        System.out.println("画了一个" + color + "的圆形");
    }
}
