package com.samin.coding.Q25;

import java.util.function.Function;

public class FunctionUseCase {

    public static void main(String[] args) {
        // 最基础的使用
        Function<String, Integer> func = Integer::valueOf;
        Integer num = func.apply("100");
        System.out.println("the num is :" + num);

        // 可以声明多个组合
        Function<String, Integer> func1 = Integer::valueOf;
        Function<Integer, Integer> func2 = x -> x + 1;
        Integer numPlus = func1.andThen(func2).apply("100");

        System.out.println("the num after plus 1 is :" + numPlus);
    }
}
