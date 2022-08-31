package collection.q2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 迭代器入门
 *
 * @author samin
 * @date 2021-01-10
 */
public class IteratorUseCase {

    /**
     * 迭代器的工作是用于遍历集合
     */
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList("a", "B", "c"));
        for (String ele : list) {
            if ("c".equals(ele)) {
                list.remove(ele);
            }
        }

        // 应该使用迭代器删除
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String ele = iterator.next();
            if ("c".equals(ele)) {
                iterator.remove();
            }
        }

        // Map的迭代器
        Map<Integer, String> map = new HashMap();
        map.put(1, "Java");
        map.put(2, "C++");
        map.put(3, "PHP");
        Iterator<Map.Entry<Integer, String>> entryIterator = map.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<Integer, String> entry = entryIterator.next();
            System.out.println(entry.getKey() + entry.getValue());
        }
    }
}
