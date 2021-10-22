package collection.q1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/**
 * 集合排序方法
 *
 * <p>Comparator 配合Collection.sort()使用
 *
 * <p>Comparable 实现后才能使TreeMap等带排序功能的类生效
 *
 * @author samin
 * @date 2021-01-10
 */
public class CompareUseCase {

    public static void main(String[] args) {
        List<SortObj> list = new ArrayList<>();
        SortObj o1 = new SortObj(1);
        SortObj o2 = new SortObj(2);
        SortObj o3 = new SortObj(3);
        SortObj o4 = new SortObj(4);
        SortObj o5 = new SortObj(5);
        list.add(o1);
        list.add(o2);
        list.add(o3);
        list.add(o4);
        list.add(o5);
        System.out.println("原始列表：" + list);

        // 使用Comparator
        Collections.sort(
                list,
                (e1, e2) -> {
                    System.out.println("使用 Comparator 接口实现方法排序");
                    return e1.weight - e2.weight;
                });
        System.out.println("Comparator排序后：" + list);

        // 使用TreeMap，使用到了Comparable实现的方法
        TreeMap<SortObj, Integer> treeMap = new TreeMap();
        treeMap.put(o1, 1);
        treeMap.put(o2, 2);
        treeMap.put(o3, 3);
        treeMap.put(o4, 4);
        treeMap.put(o5, 5);
        System.out.println(treeMap);

        /*
        Comparable相当于“内部比较器”，而Comparator相当于“外部比较器”
        当对象同时实现了 Comparable 接口和使用Collections.sort 传入了 Comparator 接口方法
        会优先调用 Comparator 接口的 compare 方法
         */
    }
}
