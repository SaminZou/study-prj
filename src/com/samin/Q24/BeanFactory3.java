package com.samin.Q24;

public class BeanFactory3<T extends Foo> {
    public T getBean(T t) {
        t.foo();
        return t;
    }
}
