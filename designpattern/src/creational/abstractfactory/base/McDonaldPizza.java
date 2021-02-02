package creational.abstractfactory.base;

/**
 * 麦当劳披萨类
 *
 * @author samin
 * @date 2021-02-02
 */
public class McDonaldPizza implements McDonaldProduct {

    @Override
    public void product() {
        System.out.println("麦当劳披萨完成");
    }
}
