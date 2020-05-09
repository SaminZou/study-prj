package com.samin.Q12;

public class PassByValueUseCase {
    private static class User {
        String name;
    }

    public static void main(String[] args) {
        // 对象入参修改属性，值发生改变，入参内存地址一直一样
        User user = new User();
        user.name = "test";
        System.out.println(System.identityHashCode(user));
        PassByValueUseCase.test(user);
        System.out.println(System.identityHashCode(user));
        System.out.println("------------------------------------");

        // 基本类型入参，值未发生改变，入参内存地址不一样，说明是拷贝了一份使用
        int a = 1;
        System.out.println(System.identityHashCode(a));
        PassByValueUseCase.test(a);
        System.out.println(System.identityHashCode(a));
    }

    public static void test(User user) {
        user.name = "samin";
        System.out.println(System.identityHashCode(user));
    }

    public static void test(int b) {
        b = 3;
        System.out.println(System.identityHashCode(b));
    }
}
