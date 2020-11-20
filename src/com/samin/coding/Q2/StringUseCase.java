package com.samin.coding.Q2;

public class StringUseCase {
    private static final String abc = "123"; // 常量

    public static void main(String[] args) {
        // ---------------------------- 字符串初始化后在常量池 -------------------------------
        String a = "123"; // 字符对象

        // 以下打印的内存地址都相同
        System.out.println(System.identityHashCode("123"));
        System.out.println(System.identityHashCode(abc));
        System.out.println(System.identityHashCode(a));
        System.out.println("------------------------");

        // System.out.println(a.equals("123"));
        // String类型变量可能会空指针，所以判断的时候可以用以下方式避免报错
        System.out.println("123".equals(a));

        // ---------------------------- 字符切割 -------------------------------
        // 以"."为分隔符
        String str1 = "192.168.110.1";
        for (String ele : str1.split("\\.")) {
            System.out.println(ele);
        }
        System.out.println("-----------------------------");

        // 以"|"为分隔符
        String str2 = "aa|bb|cc|dd";
        for (String ele : str2.split("\\|")) {
            System.out.println(ele);
        }
        System.out.println("-----------------------------");

        // 需要多个分隔符，用"|"作为连接符
        String str3 = "1@2@3&4&5&6";
        for (String ele : str3.split("@|&")) {
            System.out.println(ele);
        }
    }
}
