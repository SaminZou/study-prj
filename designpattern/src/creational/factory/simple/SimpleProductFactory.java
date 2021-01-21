package creational.factory.simple;

import creational.factory.base.IceCream;
import creational.factory.base.Pizza;
import creational.factory.base.Product;

/**
 * 简单工厂模式
 *
 * @author samin
 * @date 2021-01-21
 */
public class SimpleProductFactory {

    public static Product create(String type) {
        if ("0".equals(type)) {
            return new IceCream();
        }

        if ("1".equals(type)) {
            return new Pizza();
        }

        return null;
    }
}
