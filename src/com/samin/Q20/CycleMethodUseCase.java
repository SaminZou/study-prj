package com.samin.Q20;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class CycleMethodUseCase {
    public static void main(String[] args) {
        // 列表的循环
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            list.add(i);
        }

        Long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            Integer temp = list.get(i);
        }
        System.out.println("1 time:" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (Integer ele : list) {
            Integer temp = ele;
        }
        System.out.println("2 time:" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        Iterator<Integer> integerIterator = list.iterator();
        while (integerIterator.hasNext()) {
            Integer temp = integerIterator.next();
        }
        System.out.println("3 time:" + (System.currentTimeMillis() - start));

        // 链表的循环
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 10000; i++) {
            linkedList.add(i);
        }

        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            Integer temp = linkedList.get(i);
        }
        System.out.println("1 time:" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (Integer ele : linkedList) {
            Integer temp = ele;
        }
        System.out.println("2 time:" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        Iterator<Integer> integerIterator2 = linkedList.iterator();
        while (integerIterator2.hasNext()) {
            Integer temp = integerIterator2.next();
        }
        System.out.println("3 time:" + (System.currentTimeMillis() - start));

        /*
        结论：
        1. 列表的循环速度，index循环 > 迭代器循环 > 增强型循环
        2. 链表的循环速度，迭代器循环 > 增强型 > index循环
        3. 链表的index循环速度非常糟糕
        4. 统一结论为，建议使用迭代器进行循环遍历，不管速度和数据安全性都比较高
         */
    }
}
