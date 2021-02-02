package creational.abstractfactory.base;

/**
 * KFC披萨类
 *
 * @author samin
 * @date 2021-02-02
 */
public class KFCPizza implements KFCProduct {

    @Override
    public void product() {
        System.out.println("KFC披萨完成");
    }
}
