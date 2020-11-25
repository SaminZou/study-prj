package com.samin.designpattern.visitor;

public class Charger extends Visitor {

    @Override
    public void visitor(MedicineA a) {
        System.out.println("划价员：" + name + "给普通药" + a.getName() + "划价:" + a.getPrice());
    }

    @Override
    public void visitor(MedicineB b) {
        System.out.println("划价员：" + name + "给处方药" + b.getName() + "划价:" + b.getPrice());
    }
}
