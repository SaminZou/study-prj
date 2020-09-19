package com.samin.designpattern.mediator;

import java.util.ArrayList;
import java.util.List;

public class MediatorStructure extends Mediator {

    // 首先中介结构必须知道所有房主和租房者的信息
    private List<HouseOwner> houseOwner = new ArrayList<>();
    private List<Tenant> tenant = new ArrayList<>();

    public List<HouseOwner> getHouseOwner() {
        return houseOwner;
    }

    public void setHouseOwner(HouseOwner houseOwner) {
        this.houseOwner.add(houseOwner);
    }

    public List<Tenant> getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant.add(tenant);
    }

    public void constact(String message, Person person) {
        if (person instanceof HouseOwner) { // 如果是房主，则租房者获得信息
            for (Tenant tenant : tenant) {
                tenant.getMessage(message);
            }
        } else { // 反正则是房主获得信息
            for (HouseOwner houseOwner : houseOwner) {
                houseOwner.getMessage(message);
            }
        }
    }
}
