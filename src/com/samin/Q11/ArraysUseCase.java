package com.samin.Q11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
Arrays.asList()将数组转换为集合后，底层其实还是数组，无法使用add/remove/clear方法，体现的是适配器模式，只是转换接口，后台的数据仍是数组
 */
public class ArraysUseCase {
    public static void main(String[] args) {
        // Arrays.asList()是泛型方法，传入的对象必须是对象数组
        int[] myArray = {1, 2, 3};
        List myList = Arrays.asList(myArray);
        System.out.println(myList.get(1)); // 报错，因为只是被转换成一个二维数组
        // 使用包装类型数组可以解决
        Integer[] myArray2 = {1, 2, 3};
        List myList2 = Arrays.asList(myArray2);
        myList2.add(1); // 报错，没有实现增删方法
        // 返回的不是 ArrayList 而是里面的一个内部类 Arrays
        System.out.println(myList2.getClass());

        // 真正实现 array to list
        // 1. 最简便的方法
        List myList3 = new ArrayList(Arrays.asList(1, 2, 3));
        myList3.add(4);
        // 2. java8的stream
        Integer[] myArrays3 = {1, 2, 3};
        List myList4 = Arrays.stream(myArrays3).collect(Collectors.toList());
        // 基本类型也可以实现转换
        int[] myArrays4 = {1, 2, 3};
        List myList5 = Arrays.stream(myArrays4).boxed().collect(Collectors.toList());
    }
}
