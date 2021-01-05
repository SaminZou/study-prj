package behavioural.visitor;

public class WorkerOfPharmacy extends Visitor {

    @Override
    public void visitor(MedicineA a) {
        System.out.println("药房工作者：" + name + "拿普通药 ：" + a.getName());
    }

    @Override
    public void visitor(MedicineB b) {
        System.out.println("药房工作者：" + name + "拿处方药 ：" + b.getName());
    }
}
