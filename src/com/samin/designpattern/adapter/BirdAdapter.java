package com.samin.project.adapter;

public class BirdAdapter implements Robot {
    Bird bird;

    public BirdAdapter(Bird bird) {
        this.bird = bird;
    }

    public void cry() {
        bird.jiji();
    }

    public void move() {
        bird.fly();
    }
}
