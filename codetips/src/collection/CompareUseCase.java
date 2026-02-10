package collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Comparable 和 Comparator 的区别详解
 *
 * <p>Comparable (内部比较器)：
 * - 对象自身实现 compareTo() 方法
 * - 适用于自然排序（如数字大小、字符串字典序）
 * - 用于 TreeMap、TreeSet 等需要排序的集合
 *
 * <p>Comparator (外部比较器)：
 * - 独立于对象的比较器接口
 * - 适用于多种排序策略
 * - 用于 Collections.sort()、Arrays.sort() 等
 *
 * <p>优先级规则：
 * - 当同时存在 Comparable 和 Comparator 时，优先使用 Comparator
 * - 这种设计允许灵活覆盖默认排序行为
 *
 * @author samin
 * @date 2021-01-10
 * @version 2.0 - 优化可读性，增加实用用例
 */
public class CompareUseCase {

    public static void main(String[] args) {
        System.out.println("=== Comparable 和 Comparator 使用示例 ===\n");

        // 示例1：基本使用场景
        basicUsageExample();

        // 示例2：多字段排序
        multiFieldSortExample();

        // 示例3：自定义排序策略
        customSortStrategyExample();

        // 示例4：Java 8 函数式写法
        java8FunctionalExample();

        // 示例5：TreeMap 排序验证
        treeMapSortExample();
    }

    /**
     * 示例1：基本使用场景 - 演示 Comparable 和 Comparator 的基本用法
     */
    private static void basicUsageExample() {
        System.out.println("1. 基本使用场景：");

        List<SortObj> list = new ArrayList<>();
        list.add(new SortObj(3, "Apple"));
        list.add(new SortObj(4, "Banana"));
        list.add(new SortObj(5, "Cherry"));
        list.add(new SortObj(1, "Date"));
        list.add(new SortObj(2, "Elderberry"));

        System.out.println("原始列表：" + list);

        // 使用 Comparable (自然排序)
        Collections.sort(list);
        System.out.println("使用 Comparable 排序后：" + list);

        // 使用 Comparator (自定义排序)
        list.sort((e1, e2) -> Integer.compare(e2.weight, e1.weight)); // 降序
        System.out.println("使用 Comparator 降序排序：" + list);

        System.out.println();
    }

    /**
     * 示例2：多字段排序 - 演示按多个字段排序
     */
    private static void multiFieldSortExample() {
        System.out.println("2. 多字段排序：");

        List<SortObj> list = Arrays.asList(new SortObj(3, "Apple"), new SortObj(2, "Banana"), new SortObj(3, "Cherry"),
                                           new SortObj(1, "Date"), new SortObj(2, "Apple"));

        // 先按 weight 升序，再按 name 升序
        list.sort(Comparator.comparingInt(SortObj::getWeight)
                            .thenComparing(SortObj::getName));

        System.out.println("多字段排序结果：");
        list.forEach(obj -> System.out.println("  " + obj));
        System.out.println();
    }

    /**
     * 示例3：自定义排序策略 - 演示不同的排序需求
     */
    private static void customSortStrategyExample() {
        System.out.println("3. 自定义排序策略：");

        List<SortObj> list = Arrays.asList(new SortObj(10, "Zebra"), new SortObj(5, "Apple"), new SortObj(8, "Banana"),
                                           new SortObj(3, "Cherry"));

        // 策略1：按名称长度排序
        list.sort(Comparator.comparingInt(obj -> obj.name.length()));
        System.out.println("按名称长度排序：" + list);

        // 策略2：按名称逆序排序
        list.sort(Comparator.comparing(SortObj::getName)
                            .reversed());
        System.out.println("按名称逆序排序：" + list);

        // 策略3：null安全的排序
        List<SortObj> listWithNull = new ArrayList<>(list);
        listWithNull.add(null);
        listWithNull.sort(Comparator.nullsLast(Comparator.naturalOrder()));
        System.out.println("包含null的排序：" + listWithNull);

        System.out.println();
    }

    /**
     * 示例4：Java 8 函数式写法 - 演示现代Java语法
     */
    private static void java8FunctionalExample() {
        System.out.println("4. Java 8 函数式写法：");

        List<SortObj> list = Arrays.asList(new SortObj(3, "Apple"), new SortObj(1, "Banana"), new SortObj(4, "Cherry"),
                                           new SortObj(2, "Date"));

        // 方法引用写法
        list.sort(Comparator.comparingInt(SortObj::getWeight));
        System.out.println("方法引用排序：" + list);

        // Stream API 排序
        List<SortObj> sortedByStream = list.stream()
                                           .sorted(Comparator.comparing(SortObj::getName))
                                           .collect(Collectors.toList());
        System.out.println("Stream API 排序：" + sortedByStream);

        System.out.println();
    }

    /**
     * 示例5：TreeMap 排序验证 - 演示 TreeMap 如何使用 Comparable
     */
    private static void treeMapSortExample() {
        System.out.println("5. TreeMap 排序验证：");

        SortObj o1 = new SortObj(3, "Apple");
        SortObj o2 = new SortObj(4, "Banana");
        SortObj o3 = new SortObj(5, "Cherry");
        SortObj o4 = new SortObj(1, "Date");
        SortObj o5 = new SortObj(2, "Elderberry");

        TreeMap<SortObj, String> treeMap = new TreeMap<>();
        treeMap.put(o4, "1");
        treeMap.put(o1, "3");
        treeMap.put(o2, "4");
        treeMap.put(o5, "2");
        treeMap.put(o3, "5");

        System.out.println("TreeMap 自动按 key 排序（使用 Comparable）：");
        treeMap.forEach((key, value) -> System.out.println("  " + key + " -> " + value));

        System.out.println();
    }

    /**
     * 排序对象类，实现 Comparable 接口
     */
    static class SortObj implements Comparable<SortObj> {

        private final int weight;
        private final String name;

        public SortObj(int weight, String name) {
            this.weight = weight;
            this.name = name;
        }

        public int getWeight() {
            return weight;
        }

        public String getName() {
            return name;
        }

        /**
         * 实现 Comparable 接口的自然排序
         * 使用 Integer.compare() 避免整数溢出问题
         */
        @Override
        public int compareTo(SortObj other) {
            return Integer.compare(this.weight, other.weight);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            SortObj sortObj = (SortObj) o;
            return weight == sortObj.weight && Objects.equals(name, sortObj.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(weight, name);
        }

        @Override
        public String toString() {
            return String.format("SortObj{weight=%d, name='%s'}", weight, name);
        }
    }
}
