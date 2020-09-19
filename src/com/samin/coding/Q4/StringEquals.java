package com.samin.coding.Q4;

public class StringEquals {
    private static final String abc = "123"; // 常量

    public static void main(String[] args) {
        String a = "123"; // 字符对象

        // 以下打印的内存地址都相同
        System.out.println(System.identityHashCode("123"));
        System.out.println(System.identityHashCode(abc));
        System.out.println(System.identityHashCode(a));
        System.out.println("------------------------");

        // System.out.println(a.equals("123"));
        // String类型变量可能会空指针，所以判断的时候可以用以下方式避免报错
        System.out.println("123".equals(a));
    }
}
