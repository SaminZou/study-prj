package com.samin.Q19;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class CompareUseCase {
    /*
    Comparator 配合Collection.sort()使用
    Comparable 实现后才能使TreeMap等带排序功能的类生效
     */
    public static void main(String[] args) {
        List<ObjComparable> list = new ArrayList<>();
        ObjComparable o1 = new ObjComparable(1);
        ObjComparable o2 = new ObjComparable(2);
        ObjComparable o3 = new ObjComparable(3);
        ObjComparable o4 = new ObjComparable(4);
        ObjComparable o5 = new ObjComparable(5);
        list.add(o1);
        list.add(o2);
        list.add(o3);
        list.add(o4);
        list.add(o5);
        System.out.println("原始列表：" + list);

        // 使用Comparator
        Collections.sort(list, (e1, e2) -> {
            System.out.println("使用 Comparator 接口实现方法排序");
            return e1.weight - e2.weight;
        });
        System.out.println("Comparator排序后：" + list);

        // 使用TreeMap，使用到了Comparable实现的方法
        TreeMap<ObjComparable, Integer> treeMap = new TreeMap();
        treeMap.put(o1, 1);
        treeMap.put(o2, 2);
        treeMap.put(o3, 3);
        treeMap.put(o4, 4);
        treeMap.put(o5, 5);

        /*
        当对象实现了 Comparable 接口方法和声明容器类传入实现了 Comparator 接口方法
        会优先调用 Comparator 接口的 compare 方法
         */
    }
}