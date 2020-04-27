package com.samin.Q4;

public class StringEquals {
    private static final String abc = "123"; // 常量

    public static void main(String[] args) {
        String a = "123"; // 字符对象

        // 以下打印的内存地址都相同
        System.out.println(System.identityHashCode("123"));
        System.out.println(System.identityHashCode(abc));
        System.out.println(System.identityHashCode(a));
    }
}
