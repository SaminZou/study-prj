package algorithms;

import java.util.HashMap;
import java.util.Map;

/**
 * 独一无二的出现次数
 *
 * @author samin
 * @date 2021-01-11
 */
public class UniqueOccurrences {

    public static void main(String[] args) {
        // true false true
        System.out.println(new UniqueOccurrences().uniqueOccurrences(new int[] {1, 2, 2, 1, 1, 3}));

        System.out.println(new UniqueOccurrences().uniqueOccurrences(new int[] {1, 2}));

        System.out.println(
                new UniqueOccurrences()
                        .uniqueOccurrences(new int[] {-3, 0, 1, -3, 1, 1, 1, -3, 10, 0}));
    }

    public boolean uniqueOccurrences(int[] arr) {
        if (arr.length == 1) {
            return true;
        }

        // 计数
        Map<Integer, Integer> counter = new HashMap<>();
        for (int ele : arr) {
            if (counter.containsKey(ele)) {
                counter.put(ele, counter.get(ele) + 1);
            } else {
                counter.put(ele, 1);
            }
        }

        // 验证是否有重复计数
        Map<Integer, Integer> res = new HashMap<>();
        for (Integer ele : counter.keySet()) {
            if (res.containsKey(counter.get(ele))) {
                return false;
            } else {
                res.put(counter.get(ele), null);
            }
        }

        return true;
    }
}
