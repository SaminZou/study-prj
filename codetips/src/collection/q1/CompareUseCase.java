package collection.q1;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * 集合排序方法
 *
 * <p>Comparator 配合 Collection.sort() 使用
 *
 * <p>Comparable 实现后才能使 TreeMap 等带排序功能的类生效
 *
 * @author samin
 * @date 2021-01-10
 */
public class CompareUseCase {

    public static void main(String[] args) {
        List<SortObj> list = new ArrayList<>();
        SortObj o1 = new SortObj(3);
        SortObj o2 = new SortObj(4);
        SortObj o3 = new SortObj(5);
        SortObj o4 = new SortObj(1);
        SortObj o5 = new SortObj(2);
        list.add(o1);
        list.add(o2);
        list.add(o3);
        list.add(o4);
        list.add(o5);
        System.out.println("原始列表：" + list);

        // 使用 Comparator
        list.sort((e1, e2) -> {
            // System.out.println("使用 Comparator 接口实现方法排序");
            // 升序
            return e1.weight - e2.weight;
        });
        // 语法糖写法
        // list.sort(Comparator.comparingInt(e -> e.weight));
        System.out.println("Comparator 排序后：" + list);

        // 使用 TreeMap，key 会调用 Comparable 实现方法进行排序
        TreeMap<SortObj, String> treeMap = new TreeMap<>();
        treeMap.put(o4, "1");
        treeMap.put(o1, "3");
        treeMap.put(o2, "4");
        treeMap.put(o5, "2");
        treeMap.put(o3, "5");
        System.out.println("TreeMap 会调用排序方法：");
        // 遍历 TreeMap
        for (SortObj key : treeMap.keySet()) {
            System.out.println(key + ":" + treeMap.get(key));
        }

        /*
        Comparable 相当于 "内部比较器"，而 Comparator 相当于 "外部比较器"
        当对象同时实现了 Comparable 接口和使用 Collections.sort 传入了 Comparator 接口方法
        会优先调用 Comparator 接口的 compare 方法
         */
    }
}
