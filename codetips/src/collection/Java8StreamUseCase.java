package collection;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Java8 stream 操作
 * <p>
 * Stream 将要处理的元素集合看作一种流，在流的过程中，借助 Stream API对流中的元素进行操作，比如：筛选、排序、聚合等。
 *
 * @author samin
 * @date 2021-12-09
 */
public class Java8StreamUseCase {

    public static void main(String[] args) {
        Obj obj1 = new Obj("foo1", 4);
        Obj obj2 = new Obj("foo2", 2);
        Obj obj3 = new Obj("foo3", 5);
        Obj obj4 = new Obj("foo4", 3);
        Obj obj5 = new Obj("foo5", 1);

        List<Obj> list = new ArrayList<>();
        list.add(obj1);
        list.add(obj2);
        list.add(obj3);
        list.add(obj4);
        list.add(obj5);

        System.out.println(list);
        System.out.println(list.stream()
                .map(Obj::getFoo)
                .collect(Collectors.joining(";")));

        // 升序
        System.out.println(list.stream()
                .sorted(Comparator.comparing(Obj::getBar))
                .collect(Collectors.toList()));
        // 倒序
        System.out.println(list.stream()
                .sorted(Comparator.comparing(Obj::getBar)
                        .reversed())
                .collect(Collectors.toList()));

        // stream 和 parallelStream 的简单区分： stream 是顺序流，由主线程按顺序对流执行操作
        // parallelStream 是并行流，内部以多线程并行执行的方式对流进行操作，但前提是流中的数据处理没有顺序要求
        System.out.println(list.stream()
                .parallel()
                .filter(e -> e.getBar() > 2)
                .collect(Collectors.toList()));

        // 获取 bar 最大的元素
        Optional<Obj> max = list.stream()
                .max(Comparator.comparingInt(Obj::getBar));
        max.ifPresent(System.out::println);

        // 大于 6 的元素集合
        System.out.println(list.stream()
                .filter(e -> e.getBar() > 2)
                .count());

        // 快速声明方式，JDK8 没有
        List<Obj> list2 = List.of(new Obj("foo1", 1), new Obj("foo2", 1), new Obj("foo1", 3), new Obj("foo2", 3), new Obj("foo1", 2), new Obj("foo3", 2), new Obj("foo1", 4), new Obj("foo1", 5), new Obj("foo2", 2), new Obj("foo3", 1), new Obj("foo3", 1));
        // JDK 用以下方式替代
        // list2 = Arrays.asList(new Obj("foo1", 1), new Obj("foo2", 1), new Obj("foo1", 3), new Obj("foo2", 3),
        //         new Obj("foo1", 2), new Obj("foo3", 2), new Obj("foo1", 4), new Obj("foo1", 5), new Obj("foo2", 2)
        //         , new Obj("foo3", 1), new Obj("foo3", 1));

        // list to  Map<String, List<Obj>>
        Map<String, List<Obj>> collect = list2.stream()
                .collect(Collectors.groupingBy(Obj::getFoo));
        collect.forEach((k, v) -> System.out.printf("key: [%s], value: [%s]\n", k, v));

        // list to Map<String,Set<Integer>>
        // foo3 有两个 bar = 1 的相同对象，可以转换为 Map<String, Set<Obj>>
        Map<String, Set<Integer>> collect2 = list2.stream()
                .collect(Collectors.groupingBy(Obj::getFoo, Collectors.mapping(Obj::getBar, Collectors.toSet())));
        collect2.forEach((k, v) -> System.out.printf("key: [%s], value: [%s]\n", k, v));

        // list to Map<String, Integer>
        // (oldValue, newValue) -> newValue：这是一个合并函数，用于处理当遇到相同的键时如何合并值。在这里，如果遇到相同的键，则选择新值（newValue）作为最终的值
        Map<String, Integer> collect3 = list2.stream()
                .collect(Collectors.toMap(Obj::getFoo, Obj::getBar, (oldValue, newValue) -> newValue));
        collect3.forEach((k, v) -> System.out.printf("key: [%s], value: [%s]\n", k, v));

        // list to Map<String, Obj>，Function.identity() 代表对象实例本身
        Map<String, Obj> collect4 = list2.stream()
                .collect(Collectors.toMap(Obj::getFoo, Function.identity(), (oldValue, newValue) -> newValue));
        collect4.forEach((k, v) -> System.out.printf("key: [%s], value: [%s]\n", k, v));

        // list to Set<String>
        Set<String> collect5 = list2.stream()
                .map(Obj::getFoo)
                .collect(Collectors.toSet());
        collect5.forEach((v) -> System.out.printf("value: [%s]\n", v));

        // list split，以下例子根据 foo 字段特定条件进行分组
        Map<Boolean, List<Obj>> collect6 = list2.stream()
                .collect(Collectors.partitioningBy(obj -> "foo1".equals(obj.getFoo())));
        List<Obj> collect7 = collect6.get(true);
        collect7.forEach((v) -> System.out.printf("collect7 value: [%s]\n", v));
    }

    private static class Obj {

        public Obj(String foo, int bar) {
            this.foo = foo;
            this.bar = bar;
        }

        private final String foo;
        private final int bar;

        public String getFoo() {
            return foo;
        }

        public int getBar() {
            return bar;
        }

        @Override
        public String toString() {
            return "Obj{" + "foo='" + foo + '\'' + ", bar=" + bar + '}';
        }
    }
}
