package behavioural.visitor.visitor;

import behavioural.visitor.element.MedicineA;
import behavioural.visitor.element.MedicineB;

public class WorkerOfPharmacy extends Visitor {

    @Override
    public void visit(MedicineA a) {
        System.out.println("药房工作者：" + name + "拿普通药 ：" + a.getName());
    }

    @Override
    public void visit(MedicineB b) {
        System.out.println("药房工作者：" + name + "拿处方药 ：" + b.getName());
    }
}
