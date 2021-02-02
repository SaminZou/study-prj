package creational.abstractfactory;

import creational.abstractfactory.base.KFCPizza;
import creational.abstractfactory.base.KFCProduct;
import creational.abstractfactory.base.McDonaldPizza;
import creational.abstractfactory.base.McDonaldProduct;

/**
 * 披萨制造工厂
 *
 * @author samin
 * @date 2021-02-02
 */
public class PizzaFacotry implements IProductFactory {

    @Override
    public KFCProduct createKFCProduct() {
        return new KFCPizza();
    }

    @Override
    public McDonaldProduct createMcDonaldProduct() {
        return new McDonaldPizza();
    }
}
