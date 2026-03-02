package collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * ArrayList 用例演示
 *
 * <p>本类展示了 Java 中 ArrayList 的常见用法、最佳实践和性能注意事项。
 * 包含基础操作、Java 8+ 流式操作、错误处理、性能比较等示例。
 * </p>
 *
 * <h3>主要功能：</h3>
 * <ul>
 *   <li>ArrayList 基础操作（增删改查）</li>
 *   <li>Java 8+ Stream API 使用</li>
 *   <li>错误处理和空安全</li>
 *   <li>性能对比和最佳实践</li>
 *   <li>迭代器安全使用</li>
 * </ul>
 *
 * @author Samin
 * @date 2025-06-04
 * @version 2.0
 */
public class ListUseCase {

    /**
     * 演示 ArrayList 的常见操作
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        System.out.println("===== ArrayList 用例演示 =====\n");

        // 基础操作演示
        basicOperations();

        // 迭代器操作演示
        iteratorOperations();

        // Java 8+ 流式操作演示
        streamOperations();

        // 错误处理和空安全演示
        errorHandling();

        // 性能对比演示
        performanceComparison();

        System.out.println("\n===== 演示结束 =====");
    }

    /**
     * ArrayList 基础操作
     * 展示增删改查等基本操作
     */
    private static void basicOperations() {
        System.out.println("===== 基础操作 =====");

        // 创建 ArrayList 的几种方式
        List<String> fruits = new ArrayList<>();
        List<String> initializedList = new ArrayList<>(Arrays.asList("Apple", "Banana", "Orange"));
        List<String> copyList = new ArrayList<>(initializedList);

        System.out.println("初始列表: " + initializedList);
        System.out.println("拷贝列表: " + copyList);

        // 添加元素
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");
        fruits.add(1, "Grape"); // 在指定位置插入
        fruits.addAll(Arrays.asList("Mango", "Peach")); // 批量添加

        System.out.println("添加后: " + fruits);

        // 获取元素
        System.out.println("第一个元素: " + fruits.get(0));
        System.out.println("最后一个元素: " + fruits.get(fruits.size() - 1));

        // 修改元素
        fruits.set(0, "Red Apple");
        System.out.println("修改后: " + fruits);

        // 删除元素
        fruits.remove("Banana");
        fruits.remove(0); // 删除指定位置
        System.out.println("删除后: " + fruits);

        // 查找元素
        System.out.println("包含 Orange: " + fruits.contains("Orange"));
        System.out.println("Orange 索引: " + fruits.indexOf("Orange"));
        System.out.println("最后一个 Orange 索引: " + fruits.lastIndexOf("Orange"));

        // 清空和判断
        System.out.println("列表是否为空: " + fruits.isEmpty());
        System.out.println("列表大小: " + fruits.size());

        fruits.clear();
        System.out.println("清空后是否为空: " + fruits.isEmpty());
        System.out.println();
    }

    /**
     * 迭代器操作
     * 展示安全遍历和删除
     */
    private static void iteratorOperations() {
        System.out.println("===== 迭代器操作 =====");

        List<String> colors = new ArrayList<>(Arrays.asList("Red", "Green", "Blue", "Yellow", "Red"));
        System.out.println("原始列表: " + colors);

        // 传统 for 循环（注意并发修改问题）
        System.out.println("\n传统 for 循环:");
        for (int i = 0; i < colors.size(); i++) {
            System.out.println("  " + i + ": " + colors.get(i));
        }

        // 增强 for 循环（只读遍历）
        System.out.println("\n增强 for 循环:");
        for (String color : colors) {
            System.out.println("  " + color);
        }

        // 使用迭代器安全删除
        System.out.println("\n使用迭代器删除 'Red':");
        Iterator<String> iterator = colors.iterator();
        while (iterator.hasNext()) {
            String color = iterator.next();
            if ("Red".equals(color)) {
                iterator.remove(); // 安全删除
            }
        }
        System.out.println("删除后: " + colors);

        // 使用 ListIterator 双向遍历
        System.out.println("\nListIterator 双向遍历:");
        ListIterator<String> listIterator = colors.listIterator();
        while (listIterator.hasNext()) {
            System.out.println("  正向: " + listIterator.next());
        }
        while (listIterator.hasPrevious()) {
            System.out.println("  反向: " + listIterator.previous());
        }
        System.out.println();
    }

    /**
     * Java 8+ 流式操作
     * 展示 Stream API 的强大功能
     */
    private static void streamOperations() {
        System.out.println("===== Stream API 操作 =====");

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("原始数字: " + numbers);

        // 过滤和转换
        List<Integer> evenNumbers = numbers.stream()
                                           .filter(n -> n % 2 == 0)
                                           .collect(Collectors.toList());
        System.out.println("偶数: " + evenNumbers);

        // 映射转换
        List<String> numberStrings = numbers.stream()
                                            .map(n -> "Number: " + n)
                                            .collect(Collectors.toList());
        System.out.println("转换后: " + numberStrings);

        // 排序
        List<Integer> sortedDesc = numbers.stream()
                                          .sorted(Comparator.reverseOrder())
                                          .collect(Collectors.toList());
        System.out.println("降序排序: " + sortedDesc);

        // 聚合操作
        int sum = numbers.stream()
                         .mapToInt(Integer::intValue)
                         .sum();
        System.out.println("总和: " + sum);

        Optional<Integer> max = numbers.stream()
                                       .max(Integer::compareTo);
        System.out.println("最大值: " + max.orElse(0));

        // 分组
        Map<Boolean, List<Integer>> partitioned = numbers.stream()
                                                         .collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println("奇偶分组: " + partitioned);

        // 去重
        List<Integer> duplicates = Arrays.asList(1, 2, 2, 3, 4, 4, 5);
        List<Integer> distinct = duplicates.stream()
                                           .distinct()
                                           .collect(Collectors.toList());
        System.out.println("去重后: " + distinct);
        System.out.println();
    }

    /**
     * 错误处理和空安全
     * 展示常见的错误场景和解决方案
     */
    private static void errorHandling() {
        System.out.println("===== 错误处理和空安全 =====");

        List<String> safeList = new ArrayList<>();

        // 空安全操作
        System.out.println("空列表操作:");
        System.out.println("  列表大小: " + safeList.size());
        System.out.println("  是否为空: " + safeList.isEmpty());

        // 索引越界处理
        try {
            // 这会抛出 IndexOutOfBoundsException
            String element = safeList.get(0);
            System.out.println("第一个元素: " + element);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("索引越界异常: " + e.getMessage());
        }

        // 空指针安全
        List<String> nullList = null;
        try {
            // 这会抛出 NullPointerException
            nullList.add("test");
        } catch (NullPointerException e) {
            System.out.println("空指针异常: 不能对 null 列表执行操作");
        }

        // 安全的空列表检查
        if (nullList != null) {
            nullList.add("safe");
        } else {
            System.out.println("检测到 null 列表，跳过操作");
        }

        // 使用 Objects.requireNonNull 进行防御性编程
        try {
            Objects.requireNonNull(nullList, "列表不能为 null");
        } catch (NullPointerException e) {
            System.out.println("防御性检查: " + e.getMessage());
        }

        // 批量添加 null 值处理
        List<String> mixedList = new ArrayList<>();
        List<String> sourceWithNulls = Arrays.asList("A", null, "C", null, "E");

        // 过滤掉 null 值
        mixedList.addAll(sourceWithNulls.stream()
                                        .filter(Objects::nonNull)
                                        .collect(Collectors.toList()));
        System.out.println("过滤 null 后的列表: " + mixedList);
        System.out.println();
    }

    /**
     * 性能对比
     * 展示不同操作的性能特点
     */
    private static void performanceComparison() {
        System.out.println("===== 性能对比 =====");

        // 创建大型列表用于测试
        List<Integer> largeList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            largeList.add(i);
        }

        // 不同遍历方式的性能特点
        System.out.println("遍历方式对比:");
        System.out.println("  1. 传统 for 循环 - 最快，适合随机访问");
        System.out.println("  2. 增强 for 循环 - 简洁，适合顺序访问");
        System.out.println("  3. 迭代器遍历 - 安全，适合删除操作");
        System.out.println("  4. Stream API - 功能强大，适合复杂处理");

        // 添加操作的性能
        System.out.println("\n添加操作性能:");
        System.out.println("  1. 尾部添加 (add()) - O(1) 平均");
        System.out.println("  2. 头部添加 (add(0, element)) - O(n)");
        System.out.println("  3. 中间插入 - O(n)");

        // 搜索操作性能
        System.out.println("\n搜索操作性能:");
        System.out.println("  1. 按索引获取 (get(index)) - O(1)");
        System.out.println("  2. 按值查找 (contains(value)) - O(n)");
        System.out.println("  3. 按值查找索引 (indexOf(value)) - O(n)");

        // 内存使用建议
        System.out.println("\n内存使用建议:");
        System.out.println("  1. 预估大小时使用带初始容量的构造函数");
        System.out.println("  2. 及时调用 trimToSize() 释放多余空间");
        System.out.println("  3. 考虑使用 LinkedList 如果频繁在头部插入");
        System.out.println();
    }
}