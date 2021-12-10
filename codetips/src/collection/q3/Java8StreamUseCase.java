package collection.q3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Java8 stream 操作
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
