package collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * 容器的遍历方法比较
 *
 * <p>遍历方式及性能测试
 *
 * @author samin
 * @date 2021-01-10
 */
public class CycleMethodUseCase {

    public static void main(String[] args) {
        int listTestElementNum = 100000000;
        int linkListTestElementNum = 100000;

        // 列表的循环
        ArrayList<Integer> list = new ArrayList<>();
        // index 循环
        for (int i = 0; i < listTestElementNum; i++) {
            list.add(i);
        }

        long start = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            Integer temp = list.get(i);
        }
        System.out.println("list 1 index time:" + (System.currentTimeMillis() - start));

        // 增强型循环
        start = System.currentTimeMillis();
        for (Integer ele : list) {
            Integer temp = ele;
        }
        System.out.println("list 2 enhance time:" + (System.currentTimeMillis() - start));

        // 迭代器循环
        start = System.currentTimeMillis();
        Iterator<Integer> integerIterator = list.iterator();
        while (integerIterator.hasNext()) {
            Integer temp = integerIterator.next();
        }
        System.out.println("list 3 iterator time:" + (System.currentTimeMillis() - start));

        // 链表的循环
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < linkListTestElementNum; i++) {
            linkedList.add(i);
        }

        // index 循环
        start = System.currentTimeMillis();
        for (int i = 0; i < linkedList.size(); i++) {
            Integer temp = linkedList.get(i);
        }
        System.out.println("linklist 1 index time:" + (System.currentTimeMillis() - start));

        // 增强型循环
        start = System.currentTimeMillis();
        for (Integer ele : linkedList) {
            Integer temp = ele;
        }
        System.out.println("linklist 2 enhance time:" + (System.currentTimeMillis() - start));

        // 迭代器循环
        start = System.currentTimeMillis();
        Iterator<Integer> integerIterator2 = linkedList.iterator();
        while (integerIterator2.hasNext()) {
            Integer temp = integerIterator2.next();
        }
        System.out.println("linklist 3 iterator time:" + (System.currentTimeMillis() - start));

        /*
        结论：
        1. 列表的循环速度，index 循环【优于】迭代器循环【优于】增强型循环
        2. 链表的循环速度，迭代器循环【优于】增强型循环【优于】index 循环
        3. 链表的 index 循环速度非常糟糕
        4. 统一结论为，建议使用迭代器进行循环遍历，不管速度和数据安全性都比较高
         */
    }
}
