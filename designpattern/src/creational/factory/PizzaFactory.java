package creational.factory;

import creational.factory.base.Pizza;
import creational.factory.base.Product;

/**
 * 披萨工厂
 *
 * @author samin
 * @date 2021-01-21
 */
public class PizzaFactory implements IProductFactory {

    @Override
    public Product factory() {
        return new Pizza();
    }
}
