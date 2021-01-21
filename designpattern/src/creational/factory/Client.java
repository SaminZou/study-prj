package creational.factory;

import creational.factory.base.Product;
import creational.factory.simple.SimpleProductFactory;

/**
 * 工厂模式
 *
 * @author samin
 * @date 2021-01-05
 */
public class Client {

    public static void main(String[] args) {
        // 使用简单工厂模式
        // 生产雪糕
        Product i = SimpleProductFactory.create("0");
        i.product();
        // 生产披萨
        Product p = SimpleProductFactory.create("1");
        p.product();

        System.out.println();

        // 使用工厂方法模式，解决简单工厂模式很多 if else 的问题
        // 生产雪糕
        ProductFactory iceCreamFactory = new IceCreamProductFactory();
        Product iceCream = iceCreamFactory.factory();
        iceCream.product();
        // 生产披萨
        ProductFactory pizzaFactory = new PizzaProductFactory();
        Product pizza = pizzaFactory.factory();
        pizza.product();
    }
}
