package com.samin.coding.Q24;

public class Q24Test {
    public static void main(String[] args) {
        // 分别为泛型、Object、？ 方式的 BeanFacotry 获取Bean
        // 泛型 和 Object 的声明区别
        // 1. 不需要做强制类型装换
        // 2. 在编译时，而不是运行时抛出 ClassCastException
        // 泛型 和 ？ 的区别
        // 当需要用到对应类的方法时，需要用 <? extends Class> 语法，可以使用对应的方法
        Foo tmp = new Foo();

        BeanFactory beanFactory = new BeanFactory();
        Foo foo = beanFactory.getBean(tmp);

        BeanFactory2 beanFactory2 = new BeanFactory2();
        Foo foo2 = (Foo) beanFactory2.getBean(tmp);

        BeanFactory3<Foo> beanFactory3 = new BeanFactory3<>();
        Foo foo3 = beanFactory3.getBean(tmp);

        System.out.println("tmp : " + System.identityHashCode(tmp));
        System.out.println("foo : " + System.identityHashCode(foo));
        System.out.println("foo2 : " + System.identityHashCode(foo2));
        System.out.println("foo3 : " + System.identityHashCode(foo3));
    }
}
