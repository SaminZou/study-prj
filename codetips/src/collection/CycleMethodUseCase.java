package collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 容器遍历方法比较与性能测试
 *
 * <p>本类演示了不同集合类型（ArrayList vs LinkedList）在不同遍历方式下的性能差异，
 * 并提供了多种常见的基础用例和最佳实践示例。
 *
 * <p><b>核心结论：</b>
 * <ul>
 *   <li>ArrayList：索引循环 > 迭代器循环 > 增强型循环</li>
 *   <li>LinkedList：迭代器循环 > 增强型循环 > 索引循环（索引循环性能极差）</li>
 *   <li>统一建议：优先使用迭代器进行遍历，兼顾性能和安全性</li>
 * </ul>
 *
 * @author samin
 * @date 2021-01-10
 * @version 2.0
 */
public class CycleMethodUseCase {

    // 测试数据规模常量
    private static final int ARRAY_LIST_TEST_SIZE = 1000000;  // 减少测试规模以提高执行速度
    private static final int LINKED_LIST_TEST_SIZE = 100000;

    /**
     * 主方法 - 执行性能测试和演示各种用例
     */
    public static void main(String[] args) {
        System.out.println("=== 容器遍历方法性能测试 ===\n");

        // 1. 基础性能测试
        performBasicPerformanceTest();

        // 2. 常见用例演示
        demonstrateCommonUseCases();

        // 3. 高级特性演示
        demonstrateAdvancedFeatures();

        System.out.println("\n=== 测试完成 ===");
    }

    /**
     * 基础性能测试 - 比较不同遍历方式的性能差异
     */
    private static void performBasicPerformanceTest() {
        System.out.println("1. 基础性能测试:");

        // ArrayList 性能测试
        System.out.println("\n--- ArrayList 性能测试 ---");
        ArrayList<Integer> arrayList = createArrayList(ARRAY_LIST_TEST_SIZE);

        long arrayListIndexTime = testArrayListIndexLoop(arrayList);
        long arrayListEnhancedTime = testArrayListEnhancedLoop(arrayList);
        long arrayListIteratorTime = testArrayListIteratorLoop(arrayList);

        printPerformanceResults("ArrayList", arrayListIndexTime, arrayListEnhancedTime, arrayListIteratorTime);

        // LinkedList 性能测试
        System.out.println("\n--- LinkedList 性能测试 ---");
        LinkedList<Integer> linkedList = createLinkedList(LINKED_LIST_TEST_SIZE);

        long linkedListIndexTime = testLinkedListIndexLoop(linkedList);
        long linkedListEnhancedTime = testLinkedListEnhancedLoop(linkedList);
        long linkedListIteratorTime = testLinkedListIteratorLoop(linkedList);

        printPerformanceResults("LinkedList", linkedListIndexTime, linkedListEnhancedTime, linkedListIteratorTime);
    }

    /**
     * 常见用例演示 - 展示实际开发中的常见遍历场景
     */
    private static void demonstrateCommonUseCases() {
        System.out.println("\n2. 常见用例演示:");
        
        // 用例1：字符串列表遍历
        demonstrateStringListTraversal();

        // 用例2：对象列表遍历
        demonstrateObjectListTraversal();

        // 用例3：Map 遍历
        demonstrateMapTraversal();

        // 用例4：Set 遍历
        demonstrateSetTraversal();

        // 用例5：并行流遍历
        demonstrateParallelStreamTraversal();
    }

    /**
     * 高级特性演示 - 展示 Java 8+ 的新特性
     */
    private static void demonstrateAdvancedFeatures() {
        System.out.println("\n3. 高级特性演示:");

        // 特性1：Stream API 遍历
        demonstrateStreamTraversal();

        // 特性2：forEach 方法遍历
        demonstrateForEachTraversal();

        // 特性3：ListIterator 双向遍历
        demonstrateListIteratorTraversal();

        // 特性4：并发修改异常处理
        demonstrateConcurrentModificationHandling();
    }

    // ========== 基础性能测试方法 ==========

    private static ArrayList<Integer> createArrayList(int size) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        return list;
    }

    private static LinkedList<Integer> createLinkedList(int size) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        return list;
    }

    private static long testArrayListIndexLoop(ArrayList<Integer> list) {
        long start = System.nanoTime();
        for (int i = 0; i < list.size(); i++) {
            Integer temp = list.get(i);
        }
        return TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
    }

    private static long testArrayListEnhancedLoop(ArrayList<Integer> list) {
        long start = System.nanoTime();
        for (Integer ele : list) {
            Integer temp = ele;
        }
        return TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
    }

    private static long testArrayListIteratorLoop(ArrayList<Integer> list) {
        long start = System.nanoTime();
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer temp = iterator.next();
        }
        return TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
    }

    private static long testLinkedListIndexLoop(LinkedList<Integer> list) {
        long start = System.nanoTime();
        for (int i = 0; i < list.size(); i++) {
            Integer temp = list.get(i);
        }
        return TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
    }

    private static long testLinkedListEnhancedLoop(LinkedList<Integer> list) {
        long start = System.nanoTime();
        for (Integer ele : list) {
            Integer temp = ele;
        }
        return TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
    }

    private static long testLinkedListIteratorLoop(LinkedList<Integer> list) {
        long start = System.nanoTime();
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer temp = iterator.next();
        }
        return TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
    }

    private static void printPerformanceResults(String collectionType, long indexTime, long enhancedTime,
                                                long iteratorTime) {
        System.out.println(collectionType + " 遍历性能结果:");
        System.out.println("  索引循环: " + indexTime + " ms");
        System.out.println("  增强型循环: " + enhancedTime + " ms");
        System.out.println("  迭代器循环: " + iteratorTime + " ms");

        // 性能比较
        if (indexTime < enhancedTime && indexTime < iteratorTime) {
            System.out.println("  → 推荐使用: 索引循环");
        } else if (enhancedTime < iteratorTime) {
            System.out.println("  → 推荐使用: 增强型循环");
        } else {
            System.out.println("  → 推荐使用: 迭代器循环");
        }
    }

    // ========== 常见用例演示方法 ==========

    private static void demonstrateStringListTraversal() {
        System.out.println("\n--- 用例1: 字符串列表遍历 ---");
        List<String> fruits = Arrays.asList("Apple", "Banana", "Orange", "Grape", "Mango");

        // 增强型循环（推荐）
        System.out.println("增强型循环:");
        for (String fruit : fruits) {
            System.out.println("  " + fruit.toUpperCase());
        }

        // Stream API（Java 8+）
        System.out.println("\nStream API:");
        fruits.stream()
              .map(String::toUpperCase)
              .forEach(fruit -> System.out.println("  " + fruit));
    }

    private static void demonstrateObjectListTraversal() {
        System.out.println("\n--- 用例2: 对象列表遍历 ---");
        List<Person> people = Arrays.asList(new Person("Alice", 25), new Person("Bob", 30), new Person("Charlie", 35));

        // 迭代器遍历（可安全删除元素）
        System.out.println("迭代器遍历（可删除）:");
        Iterator<Person> iterator = people.iterator();
        while (iterator.hasNext()) {
            Person person = iterator.next();
            System.out.println("  " + person.getName() + " - " + person.getAge());
            // iterator.remove(); // 安全删除当前元素
        }
    }

    private static void demonstrateMapTraversal() {
        System.out.println("\n--- 用例3: Map 遍历 ---");
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Alice", 85);
        scores.put("Bob", 92);
        scores.put("Charlie", 78);

        // EntrySet 遍历（推荐）
        System.out.println("EntrySet 遍历:");
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }

        // KeySet 遍历
        System.out.println("\nKeySet 遍历:");
        for (String key : scores.keySet()) {
            System.out.println("  " + key + ": " + scores.get(key));
        }
    }

    private static void demonstrateSetTraversal() {
        System.out.println("\n--- 用例4: Set 遍历 ---");
        Set<Integer> numbers = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));

        // 增强型循环
        System.out.println("增强型循环:");
        for (Integer num : numbers) {
            System.out.println("  " + num);
        }

        // forEach 方法（Java 8+）
        System.out.println("\nforEach 方法:");
        numbers.forEach(num -> System.out.println("  " + num));
    }

    private static void demonstrateParallelStreamTraversal() {
        System.out.println("\n--- 用例5: 并行流遍历 ---");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // 并行流处理
        System.out.println("并行流处理:");
        numbers.parallelStream()
               .map(n -> n * n)
               .forEach(n -> System.out.println("  " + n + " (线程: " + Thread.currentThread()
                                                                              .getName() + ")"));
    }

    // ========== 高级特性演示方法 ==========

    private static void demonstrateStreamTraversal() {
        System.out.println("\n--- 特性1: Stream API 遍历 ---");
        List<String> languages = Arrays.asList("Java", "Python", "JavaScript", "C++", "Go");

        // 过滤和映射
        System.out.println("过滤和映射:");
        languages.stream()
                 .filter(lang -> lang.length() > 3)
                 .map(String::toUpperCase)
                 .forEach(lang -> System.out.println("  " + lang));
    }

    private static void demonstrateForEachTraversal() {
        System.out.println("\n--- 特性2: forEach 方法遍历 ---");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // 方法引用
        System.out.println("方法引用:");
        numbers.forEach(System.out::println);

        // Lambda 表达式
        System.out.println("\nLambda 表达式:");
        numbers.forEach(n -> System.out.println("  数字: " + n));
    }

    private static void demonstrateListIteratorTraversal() {
        System.out.println("\n--- 特性3: ListIterator 双向遍历 ---");
        List<String> items = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));

        ListIterator<String> listIterator = items.listIterator();

        // 正向遍历
        System.out.println("正向遍历:");
        while (listIterator.hasNext()) {
            String item = listIterator.next();
            System.out.println("  " + item);
            if ("C".equals(item)) {
                listIterator.set("C-modified"); // 修改当前元素
            }
        }

        // 反向遍历
        System.out.println("\n反向遍历:");
        while (listIterator.hasPrevious()) {
            String item = listIterator.previous();
            System.out.println("  " + item);
        }
    }

    private static void demonstrateConcurrentModificationHandling() {
        System.out.println("\n--- 特性4: 并发修改异常处理 ---");
        List<String> items = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));

        // 错误示例：在增强型循环中修改集合
        System.out.println("错误示例（会抛出 ConcurrentModificationException）:");
        try {
            for (String item : items) {
                if ("B".equals(item)) {
                    // items.remove(item); // 这会抛出异常
                }
            }
            System.out.println("  未发生修改，正常执行");
        } catch (Exception e) {
            System.out.println("  异常: " + e.getClass()
                                             .getSimpleName());
        }

        // 正确示例：使用迭代器安全删除
        System.out.println("\n正确示例（使用迭代器安全删除）:");
        Iterator<String> iterator = items.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            if ("B".equals(item)) {
                iterator.remove(); // 安全删除
                System.out.println("  已安全删除元素: B");
            }
        }
    }

    // ========== 辅助类 ==========

    /**
     * 简单的 Person 类用于演示
     */
    static class Person {

        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {return name;}

        public int getAge() {return age;}
    }
}
