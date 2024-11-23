package behavioural.mediator;

import behavioural.mediator.component.HouseOwner;
import behavioural.mediator.component.Tenant;
import behavioural.mediator.concretemediator.MediatorStructure;

/**
 * 中介者模式
 *
 * @author samin
 * @date 2021-01-05
 */
public class DemoEntry {

    public static void main(String[] args) {
        MediatorStructure mediatorStructure = new MediatorStructure();
        // 添加房东
        HouseOwner boss = new HouseOwner("samin", mediatorStructure);
        mediatorStructure.setHouseOwner(boss);
        // 添加房东
        HouseOwner boss2 = new HouseOwner("taobao", mediatorStructure);
        mediatorStructure.setHouseOwner(boss2);
        // 添加租户
        Tenant tenant1 = new Tenant("test1", mediatorStructure);
        Tenant tenant2 = new Tenant("test2", mediatorStructure);
        Tenant tenant3 = new Tenant("test3", mediatorStructure);
        Tenant tenant4 = new Tenant("test4", mediatorStructure);
        mediatorStructure.setTenant(tenant1);
        mediatorStructure.setTenant(tenant2);
        mediatorStructure.setTenant(tenant3);
        mediatorStructure.setTenant(tenant4);

        // 使用中介执行操作
        boss2.contact("taobao有房出租啦");
        // 使用中介执行操作
        boss.contact("samin有房出租啦");
        // 使用中介执行操作
        tenant3.contact("我想要租房");
    }
}
