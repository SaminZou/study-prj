package behavioural.mediator;

/**
 * 中介者模式
 *
 * @author samin
 * @date 2021-01-05
 */
public class Client {

    public static void main(String[] args) {
        MediatorStructure mediatorStructure = new MediatorStructure();
        // 添加用户
        HouseOwner boss = new HouseOwner("samin", mediatorStructure);
        mediatorStructure.setHouseOwner(boss);
        // 添加用户
        HouseOwner boss2 = new HouseOwner("taobao", mediatorStructure);
        mediatorStructure.setHouseOwner(boss2);
        // 添加用户
        Tenant tenant1 = new Tenant("test1", mediatorStructure);
        Tenant tenant2 = new Tenant("test2", mediatorStructure);
        Tenant tenant3 = new Tenant("test3", mediatorStructure);
        Tenant tenant4 = new Tenant("test4", mediatorStructure);
        mediatorStructure.setTenant(tenant1);
        mediatorStructure.setTenant(tenant2);
        mediatorStructure.setTenant(tenant3);
        mediatorStructure.setTenant(tenant4);

        // 使用中介执行操作
        boss2.constact("taobao有房出租啦");
        // 使用中介执行操作
        boss.constact("samin有房出租啦");
        // 使用中介执行操作
        tenant3.constact("我想要租房");
    }
}
