package behavioural.visitor.visitor;

import behavioural.visitor.element.MedicineA;
import behavioural.visitor.element.MedicineB;

public abstract class Visitor {

    protected String name;

    public void setName(String name) {
        this.name = name;
    }

    public abstract void visit(MedicineA a);

    public abstract void visit(MedicineB b);
}
