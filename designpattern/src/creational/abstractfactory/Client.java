package creational.abstractfactory;

import creational.abstractfactory.base.KFCProduct;
import creational.abstractfactory.base.McDonaldProduct;

/**
 * 抽象工厂模式
 *
 * @author samin
 * @date 2021-02-02
 */
public class Client {

    public static void main(String[] args) {
        // 实例化工厂
        IProductFactory iceCreamFactory = new IceCreamFactory();
        IProductFactory pizzaFacotry = new PizzaFacotry();

        // 实例化冰淇凌
        KFCProduct kfcIceCream = iceCreamFactory.createKFCProduct();
        KFCProduct kfcPizza = pizzaFacotry.createKFCProduct();

        // 实例化披萨
        McDonaldProduct mcDonaldIceCream = iceCreamFactory.createMcDonaldProduct();
        McDonaldProduct mcDonaldPizza = pizzaFacotry.createMcDonaldProduct();

        // 产品完成
        kfcIceCream.product();
        kfcPizza.product();
        mcDonaldIceCream.product();
        mcDonaldPizza.product();
    }
}
