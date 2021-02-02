package creational.abstractfactory.base;

/**
 * KFC冰淇淋类
 *
 * @author samin
 * @date 2021-02-02
 */
public class KFCIceCream implements KFCProduct {

    @Override
    public void product() {
        // 具体的业务逻辑
        System.out.println("KFC冰淇淋完成");
    }
}
