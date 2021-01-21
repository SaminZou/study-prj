package creational.factory.base;

/**
 * 披萨类
 *
 * @author samin
 * @date 2021-01-21
 */
public class Pizza implements Product {

    @Override
    public void product() {
        System.out.println("披萨完成");
    }
}
