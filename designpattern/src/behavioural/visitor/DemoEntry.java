package behavioural.visitor;

import behavioural.visitor.element.Medicine;
import behavioural.visitor.element.MedicineA;
import behavioural.visitor.element.MedicineB;
import behavioural.visitor.visitor.Charger;
import behavioural.visitor.visitor.Visitor;
import behavioural.visitor.visitor.WorkerOfPharmacy;

/**
 * 访问者模式
 *
 * @author samin
 * @date 2021-01-05
 */
public class DemoEntry {

    public static void main(String[] args) {
        // 双分派技术：第一次确定操作者，第二次根据药物执行任务
        Medicine a = new MedicineA("板蓝根", 11.0);
        Medicine b = new MedicineA("感康", 14.3);
        Medicine c = new MedicineB("安眠药", 100.5);

        // 添加元素
        Presciption presciption = new Presciption();
        presciption.addMedicine(a);
        presciption.addMedicine(b);
        presciption.addMedicine(c);

        Visitor charger = new Charger();
        charger.setName("张三");
        Visitor workerOfPharmacy = new WorkerOfPharmacy();
        workerOfPharmacy.setName("李四");

        // 接受访问者操作
        presciption.accept(charger);
        System.out.println("-------------------------------------");
        presciption.accept(workerOfPharmacy);
    }
}
