package behavioural.visitor;

public class MedicineA extends Medicine {

    public MedicineA(String name, double price) {
        super(name, price);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitor(this);
    }
}
