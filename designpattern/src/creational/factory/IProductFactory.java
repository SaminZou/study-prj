package creational.factory;

import creational.factory.base.Product;

public interface IProductFactory {

    /**
     * 获取实际产品
     */
    Product factory();
}
