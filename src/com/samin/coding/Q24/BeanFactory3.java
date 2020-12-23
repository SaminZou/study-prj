package com.samin.coding.Q24;

/**
 * 使用继承泛型
 *
 * @author samin
 * @date 2020-12-23
 */
public class BeanFactory3<T extends Foo> {

    public T getBean(T t) {
        t.bar();
        return t;
    }
}
