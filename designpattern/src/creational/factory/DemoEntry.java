package creational.factory;

import creational.factory.base.Product;
import creational.factory.simple.SimpleProductFactory;
import java.util.Objects;

/**
 * 工厂方法模式
 *
 * @author samin
 * @date 2021-01-05
 */
public class DemoEntry {

    public static void main(String[] args) {
        // 使用简单工厂模式
        // 生产雪糕
        Product i = SimpleProductFactory.create("0");
        if (Objects.nonNull(i)) {
            i.product();
        }
        // 生产披萨
        Product p = SimpleProductFactory.create("1");
        if (Objects.nonNull(i)) {
            i.product();
        }

        System.out.println();

        // 使用工厂方法模式，解决简单工厂模式很多 if else 的问题
        // 生产雪糕
        IProductFactory iceCreamFactory = new IceCreamFactory();
        Product iceCream = iceCreamFactory.factory();
        iceCream.product();
        // 生产披萨
        IProductFactory pizzaFactory = new PizzaFactory();
        Product pizza = pizzaFactory.factory();
        pizza.product();
    }
}
