package com.samin.designpattern.decorator;

public class Espresso extends Beverage {

    public Espresso() {
        description = "Espresso";
    }

    @Override
    public double cost() {
        return 2.05;
    }
}
