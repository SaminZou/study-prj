package creational.abstractfactory.base;

/**
 * 麦当劳冰淇淋类
 *
 * @author samin
 * @date 2021-02-02
 */
public class McDonaldIceCream implements McDonaldProduct {

    @Override
    public void product() {
        // 具体的业务逻辑
        System.out.println("麦当劳冰淇淋完成");
    }
}
