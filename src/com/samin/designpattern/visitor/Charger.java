package com.samin.project.visitor;

public class Charger extends Visitor {
    public void visitor(MedicineA a) {
        System.out.println("划价员：" + name + "给普通药" + a.getName() + "划价:" + a.getPrice());
    }

    public void visitor(MedicineB b) {
        System.out.println("划价员：" + name + "给处方药" + b.getName() + "划价:" + b.getPrice());
    }
}
