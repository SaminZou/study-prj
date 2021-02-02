package creational.abstractfactory;

import creational.abstractfactory.base.KFCIceCream;
import creational.abstractfactory.base.KFCProduct;
import creational.abstractfactory.base.McDonaldIceCream;
import creational.abstractfactory.base.McDonaldProduct;

/**
 * 冰淇凌制造工厂
 *
 * @author samin
 * @date 2021-02-02
 */
public class IceCreamFactory implements IProductFactory {

    @Override
    public KFCProduct createKFCProduct() {
        return new KFCIceCream();
    }

    @Override
    public McDonaldProduct createMcDonaldProduct() {
        return new McDonaldIceCream();
    }
}
