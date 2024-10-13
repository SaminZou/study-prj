package collection;

import java.util.Iterator;
import java.util.Map;

public class HashMapUseCase {

    public static void main(String[] args) {
        Map<String, String> map = new java.util.HashMap<>();
        map.putIfAbsent("a", "b");
        System.out.println(map);
        map.putIfAbsent("a", "c");
        System.out.println(map);
        // put 方法返回值为被覆盖的值
        String r1 = map.put("1", "r1");
        System.out.println(r1);
        String r2 = map.put("1", "r2");
        System.out.println(r2);
        String r3 = map.put("1", "r3");
        System.out.println(r3);
        System.out.println(map.get(null));
        map.put("2", "3");
        System.out.println(map);

        // 报错 java.util.ConcurrentModificationException
        // for (String key: map.keySet()) {
        //     if (key.equals("1")){
        //         map.remove("2");
        //     }
        // }
        // 解决方案
        Iterator<String> iterator = map.keySet()
                .iterator();

        while (iterator.hasNext()) {
            String key = iterator.next();
            if (key.equals("1")) {
                // map.remove("2");
                // 使用迭代器的 remove()
                iterator.remove();
            }
        }
        System.out.println(map);
    }
}