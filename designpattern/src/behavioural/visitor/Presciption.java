package behavioural.visitor;

import java.util.ArrayList;
import java.util.List;

public class Presciption {

    List<Medicine> list = new ArrayList<>();

    public void accept(Visitor visitor) {
        for (Medicine medicine : list) {
            medicine.accept(visitor);
        }
    }

    public void addMedicine(Medicine medicine) {
        list.add(medicine);
    }

    public void removeMedicine(Medicine medicine) {
        list.remove(medicine);
    }
}
