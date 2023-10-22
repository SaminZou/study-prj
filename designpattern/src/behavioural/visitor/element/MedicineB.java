package behavioural.visitor.element;

import behavioural.visitor.visitor.Visitor;

public class MedicineB extends Medicine {

    public MedicineB(String name, double price) {
        super(name, price);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
