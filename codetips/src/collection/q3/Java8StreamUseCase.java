package collection.q3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Java8 stream 操作
 * <p>
 * Stream将要处理的元素集合看作一种流，在流的过程中，借助 Stream API对流中的元素进行操作，比如：筛选、排序、聚合等。
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

        System.out.println(list.stream().sorted(Comparator.comparing(Obj::getBar)).collect(Collectors.toList()));
        System.out.println(
                list.stream().sorted(Comparator.comparing(Obj::getBar).reversed()).collect(Collectors.toList()));

        // stream和parallelStream的简单区分： stream是顺序流，由主线程按顺序对流执行操作，而parallelStream是并行流，内部以多线程并行执行的方式对流进行操作，但前提是流中的数据处理没有顺序要求。
        System.out.println(list.stream().parallel().filter(e -> e.getBar() > 2).collect(Collectors.toList()));

        // 获取 bar 最大的元素
        System.out.println(list.stream().max(Comparator.comparingInt(Obj::getBar)).get());

        // 大于 6 的元素集合
        System.out.println(list.stream().filter(e -> e.getBar() > 2).count());
    }

    private static class Obj {

        public Obj(String foo, int bar) {
            this.foo = foo;
            this.bar = bar;
        }

        private String foo;
        private int bar;

        public int getBar() {
            return bar;
        }

        @Override
        public String toString() {
            return "Obj{" + "foo='" + foo + '\'' + ", bar=" + bar + '}';
        }
    }
}
