package collection.q3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * List 和数组的转换
 *
 * @author samin
 * @date 2021-01-10
 */
public class ListAndArrayUseCase {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(222);
        list.add(111);
        list.add(333);
        System.out.println("原始列表：" + list);
        // List<Integer> to int[]
        int[] ints = list.stream()
                         .mapToInt(Integer::valueOf)
                         .toArray();
        System.out.println("列表转换成数组：" + Arrays.toString(ints));

        // natural order
        list.sort(Comparator.naturalOrder());
        System.out.println("排序后列表：" + list);

        // List<Integer> to Integer[]
        Integer[] integers1 = list.toArray(new Integer[0]);
        System.out.println("列表转换成数组：" + Arrays.toString(integers1));

        // int[] to List<Integer>
        List<Integer> collect = Arrays.stream(ints)
                                      .boxed()
                                      .collect(Collectors.toList());
        System.out.println("数组转换成列表：" + collect);

        // int[] to Integer[]
        Integer[] integers = Arrays.stream(ints)
                                   .boxed()
                                   .toArray(Integer[]::new);
        System.out.println("原始数据类型数组转换成对象数组：" + Arrays.toString(integers));

        // Integer[] to int[]
        int[] ints1 = Arrays.stream(integers1)
                            .mapToInt(Integer::valueOf)
                            .toArray();
        System.out.println("对象数组转换成原始数据类型数组：" + Arrays.toString(integers1));

        // Integer[] to List<Integer>
        List<Integer> integers2 = new ArrayList<>(Arrays.asList(integers1));
        System.out.println("数组转换成列表：" + integers2);
    }
}
