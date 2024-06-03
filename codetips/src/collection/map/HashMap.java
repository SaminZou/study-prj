package collection.map;

import java.util.Map;

public class HashMap {

    public static void main(String[] args) {
        Map<String, String> map = new java.util.HashMap<>();
        // put 方法返回值为被覆盖的值
        String r1 = map.put("1", "r1");
        System.out.println(r1);
        String r2 = map.put("1", "r2");
        System.out.println(r2);
        String r3 = map.put("1", "r3");
        System.out.println(r3);
        map.put(null, "123");
        System.out.println(map.get(null));
    }
}