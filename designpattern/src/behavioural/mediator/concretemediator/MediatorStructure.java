package behavioural.mediator.concretemediator;

import behavioural.mediator.component.HouseOwner;
import behavioural.mediator.component.Person;
import behavioural.mediator.component.Tenant;
import behavioural.mediator.mediator.Mediator;
import java.util.ArrayList;
import java.util.List;

public class MediatorStructure implements Mediator {

    private final List<HouseOwner> houseOwner = new ArrayList<>();
    private final List<Tenant> tenant = new ArrayList<>();

    public void setHouseOwner(HouseOwner houseOwner) {
        this.houseOwner.add(houseOwner);
    }

    public void setTenant(Tenant tenant) {
        this.tenant.add(tenant);
    }

    @Override
    public void notify(String message, Person person) {
        // 如果是房东，发布出租信息
        if (person instanceof HouseOwner) {
            for (Tenant tenant : tenant) {
                tenant.getMessage(message);
            }
        }

        // 如果是租户，发布租房信息
        if (person instanceof Tenant) {
            for (HouseOwner houseOwner : houseOwner) {
                houseOwner.getMessage(message);
            }
        }
    }
}
