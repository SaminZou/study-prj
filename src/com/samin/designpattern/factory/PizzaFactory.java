package com.samin.designpattern.factory;

public class PizzaFactory implements Factory {

    @Override
    public Product factory() {
        return new Pizza();
    }
}
