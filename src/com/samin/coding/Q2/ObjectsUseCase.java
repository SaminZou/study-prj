package com.samin.coding.Q2;

import java.util.Objects;

public class ObjectsUseCase {
    public static void main(String[] args) {
        String a = null;
        String b = "123";
        // 这个会报空指针
        //        System.out.println(a.equals(b));

        // 正常需要这样来解决
        if (a != null) {
            System.out.println(a.equals(b));
        }

        // 或者使用常量或者确定值来解决报空指针的问题
        if ("123".equals(a)) {}

        // jdk1.7之后提供Objects工具包解决这个问题
        // Objects的equals方法是先判断入参是否为空，然后调用Object的equals方法进行比较
        System.out.println(Objects.equals(a, b));
        System.out.println("-----------------------");
        // 判断不为空
        System.out.println(Objects.nonNull(a));
        // 判断为空
        System.out.println(Objects.isNull(a));
    }
}
