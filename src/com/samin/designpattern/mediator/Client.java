package com.samin.project.mediator;

/**
 * 中介者模式
 */
public class Client {
    public static void main(String[] args) {
        MediatorStructure a = new MediatorStructure();
        HouseOwner boss = new HouseOwner("samin", a);
        HouseOwner boss2 = new HouseOwner("taobao", a);
        Tenant tenant1 = new Tenant("test1", a);
        Tenant tenant2 = new Tenant("test2", a);
        Tenant tenant3 = new Tenant("test3", a);
        Tenant tenant4 = new Tenant("test4", a);

        a.setTenant(tenant1);
        a.setTenant(tenant2);
        a.setTenant(tenant3);
        a.setTenant(tenant4);
        a.setHouseOwner(boss);
        a.setHouseOwner(boss2);
        boss.constact("samin有房出租啦");
        boss2.constact("taobao有房出租啦");
        tenant3.constact("我想要租房");
    }
}
