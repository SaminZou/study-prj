package com.samin.designpattern.visitor;

public class MedicineB extends Medicine {

    public MedicineB(String name, double price) {
        super(name, price);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitor(this);
    }
}
