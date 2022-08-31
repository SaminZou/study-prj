package creational.abstractfactory;

import creational.abstractfactory.base.KFCProduct;
import creational.abstractfactory.base.McDonaldProduct;

/**
 * 工厂类
 *
 * @author samin
 * @date 2021-02-02
 */
public interface IProductFactory {

    /**
     * 创建 KFC 产品
     */
    KFCProduct createKFCProduct();

    /**
     * 创建麦当劳产品
     */
    McDonaldProduct createMcDonaldProduct();
}
