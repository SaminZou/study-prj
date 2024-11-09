package puzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 反馈引用传递的问题
 */
public class StreamBadTaste {

    public static void main(String[] args) throws InterruptedException {
        List<Foo> list = new ArrayList<>();

        list.add(Foo.getInstance("t1", 1));
        list.add(Foo.getInstance("t2", 1));
        list.add(Foo.getInstance("t3", 1));
        list.add(Foo.getInstance("t1", 2));
        list.add(Foo.getInstance("t2", 2));

        Map<String, Foo> map1 = list.stream().collect(Collectors.toMap(Foo::getTag, item -> item, (oldValue, newValue) -> oldValue));

        Map<String, Foo> map2 = list.stream().collect(Collectors.toMap(Foo::getTag, item -> item, (oldValue, newValue) -> {
            oldValue.setNums(oldValue.getNums() + newValue.getNums());
            return oldValue;
        }));

        // 预期是： t1: 1 , t2: 1 , t3: 1
        // 实际是： t1:3 , t2: 3 , t3: 1
        System.out.println(map1);
        // t1:3 , t2: 3 , t3: 1
        System.out.println(map2);
    }

    static class Foo {

        public static Foo getInstance(String tag, Integer nums) {
            Foo foo = new Foo();

            foo.setTag(tag);
            foo.setNums(nums);

            return foo;
        }

        private String tag;
        private Integer nums;

        public String getTag() {
            return tag;
        }

        @Override
        public String toString() {
            return "Foo{" +
                    "tag='" + tag + '\'' +
                    ", nums=" + nums +
                    '}';
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public Integer getNums() {
            return nums;
        }

        public void setNums(Integer nums) {
            this.nums = nums;
        }
    }
}
