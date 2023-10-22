package behavioural.visitor.visitor;

import behavioural.visitor.element.MedicineA;
import behavioural.visitor.element.MedicineB;

public class Charger extends Visitor {

    @Override
    public void visit(MedicineA a) {
        System.out.println("划价员：" + name + "给普通药" + a.getName() + "划价:" + a.getPrice());
    }

    @Override
    public void visit(MedicineB b) {
        System.out.println("划价员：" + name + "给处方药" + b.getName() + "划价:" + b.getPrice());
    }
}
