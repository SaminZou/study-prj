package com.samin.designpattern.memo;

public class Memento {

    private int bloodFlow;
    private int magicPoint;

    public Memento(int bloodFlow, int magicPoint) {
        this.bloodFlow = bloodFlow;
        this.magicPoint = magicPoint;
    }

    public int getBloodFlow() {
        return bloodFlow;
    }

    public void setBloodFlow(int bloodFlow) {
        this.bloodFlow = bloodFlow;
    }

    public int getMagicPoint() {
        return magicPoint;
    }

    public void setMagicPoint(int magicPoint) {
        this.magicPoint = magicPoint;
    }
}
