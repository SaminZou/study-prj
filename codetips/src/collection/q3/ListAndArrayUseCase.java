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
        list.add(111);
        // List<Integer> to int[]
        int[] ints = list.stream().mapToInt(Integer::valueOf).toArray();
        System.out.println(ints.length);

        // natural order
        list.sort(Comparator.naturalOrder());

        // List<Integer> to Integer[]
        Integer[] integers1 = list.toArray(new Integer[0]);

        // int[] to List<Integer>
        List<Integer> collect = Arrays.stream(ints).boxed().collect(Collectors.toList());
        System.out.println(collect.size());

        // int[] to Integer[]
        Integer[] integers = Arrays.stream(ints).boxed().toArray(Integer[]::new);

        // Integer[] to int[]
        int[] ints1 = Arrays.stream(integers1).mapToInt(Integer::valueOf).toArray();

        // Integer[] to List<Integer>
        List<Integer> integers2 = new ArrayList<>(Arrays.asList(integers1));
    }
}
