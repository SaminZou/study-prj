package com.samin.coding.Q8;

// 修饰的接口为“函数式接口”，定义接口中的抽象方法只能有一个
@FunctionalInterface
public interface LambdaInf {
    void action(String words);

    default void actionUtils() {
        System.out.println("此方法不影响lambda表达式的使用");
    }
}
