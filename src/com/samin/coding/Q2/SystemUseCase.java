package com.samin.coding.Q2;

import java.util.Arrays;

public class SystemUseCase {

    public static void main(String[] args) {

        // 模拟从arr1复制从2开头的3个数字到arr2;
        int[] arr1 = new int[] {1, 2, 3, 4, 5};
        int[] arr2 = new int[3];
        // 入参分别是源数组，源数组开头，目的数组，目的数组开头，复制长度
        System.arraycopy(arr1, 1, arr2, 0, 3);
        System.out.println(Arrays.toString(arr2));

        // 获取对象的系统和内部地址相关的 hashCode
        // 可用于判断是否是同一个实例对象
        // 同时可以验证 hashCode() 方法是否重写过
        // String 类的 hashCode() 是重写过的，所以两者的结果自然不一样
        String foo = "foo";
        int fooHashCode = System.identityHashCode(foo);
        System.out.println(fooHashCode);
        System.out.println(foo.hashCode());
    }
}
