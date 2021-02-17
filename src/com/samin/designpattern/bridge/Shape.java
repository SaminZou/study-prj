package com.samin.designpattern.bridge;

public abstract class Shape {

    Color color;

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract void draw();
}
