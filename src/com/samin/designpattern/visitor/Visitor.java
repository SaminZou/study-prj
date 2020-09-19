package com.samin.project.visitor;

public abstract class Visitor {
    protected String name;

    public void setName(String name) {
        this.name = name;
    }

    public abstract void visitor(MedicineA a);

    public abstract void visitor(MedicineB b);
}
