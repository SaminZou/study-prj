package creational.factory;

import creational.factory.base.Pizza;
import creational.factory.base.Product;

/**
 * 披萨工厂
 *
 * @author samin
 * @date 2021-01-21
 */
public class PizzaProductFactory implements ProductFactory {

    @Override
    public Product factory() {
        return new Pizza();
    }
}
