package creational.factory;

import creational.factory.base.IceCream;
import creational.factory.base.Product;

/**
 * 冰淇淋工厂
 *
 * @author samin
 * @date 2021-01-21
 */
public class IceCreamProductFactory implements ProductFactory {

    @Override
    public Product factory() {
        return new IceCream();
    }
}
