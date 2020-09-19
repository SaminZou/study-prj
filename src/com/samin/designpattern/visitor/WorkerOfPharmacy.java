package com.samin.designpattern.visitor;

public class WorkerOfPharmacy extends Visitor {

    public void visitor(MedicineA a) {
        System.out.println("药房工作者：" + name + "拿普通药 ：" + a.getName());
    }

    public void visitor(MedicineB b) {
        System.out.println("药房工作者：" + name + "拿处方药 ：" + b.getName());
    }
}
