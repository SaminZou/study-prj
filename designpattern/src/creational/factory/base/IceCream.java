package creational.factory.base;

/**
 * 冰淇淋类
 *
 * @author samin
 * @date 2021-01-21
 */
public class IceCream implements Product {

    @Override
    public void product() {
        // 具体的业务逻辑
        System.out.println("冰淇淋完成");
    }
}
