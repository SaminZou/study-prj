package collection;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 数组与 Stream 转换及操作用例
 * <p>
 * 展示了如何在数组和 Stream 之间进行转换，以及使用 Stream API 对数组进行操作
 *
 * @author samin
 * @date 2021-12-09
 */
public class ArrayStreamUseCase {

    public static void main(String[] args) {
        // 数组转 Stream
        String[] strs = new String[]{"a", "b", "c", "d"};

        // 方法1: Arrays.stream() - 推荐，支持指定范围
        System.out.println("=== 方法1: Arrays.stream() ===");
        Stream<String> stream1 = Arrays.stream(strs);
        stream1.forEach(System.out::println);

        // 方法2: Stream.of() - 将整个数组作为一个元素或解包
        System.out.println("\n=== 方法2: Stream.of() ===");
        // 解包数组为多个元素
        Stream<String> stream2 = Stream.of(strs);
        stream2.forEach(System.out::println);

        // 数组合并示例：Stream.concat
        System.out.println("\n=== 数组合并: Stream.concat() ===");
        String[] arr1 = new String[]{"a", "b"};
        String[] arr2 = new String[]{"c", "d"};

        // 方式1: 使用 Stream.concat
        String[] merged = Stream.concat(Arrays.stream(arr1), Arrays.stream(arr2))
                .toArray(String[]::new);
        System.out.println("合并后: " + Arrays.toString(merged));

        // 方式2: 使用 Stream.of 扁平化合并
        String[] merged2 = Stream.of(arr1, arr2)
                .flatMap(Arrays::stream)
                .toArray(String[]::new);
        System.out.println("合并后(扁平化): " + Arrays.toString(merged2));

        // 追加元素示例
        System.out.println("\n=== 追加元素 ===");
        String[] baseArray = new String[]{"a", "b"};
        String[] withExtra = Stream.concat(
                Arrays.stream(baseArray),
                Stream.of("c", "d", "e")
        ).toArray(String[]::new);
        System.out.println("原数组: " + Arrays.toString(baseArray));
        System.out.println("追加后: " + Arrays.toString(withExtra));

        // 数组过滤
        System.out.println("\n=== 数组过滤 ===");
        String[] filtered = Arrays.stream(strs)
                .filter(s -> !s.equals("c"))
                .toArray(String[]::new);
        System.out.println("过滤后(不含c): " + Arrays.toString(filtered));

        // 数组映射转换
        System.out.println("\n=== 数组映射转换 ===");
        String[] upperCase = Arrays.stream(strs)
                .map(String::toUpperCase)
                .toArray(String[]::new);
        System.out.println("大写转换: " + Arrays.toString(upperCase));

        // Stream 转数组
        System.out.println("\n=== Stream 转数组 ===");
        List<String> list = Arrays.asList("x", "y", "z");
        String[] fromList = list.stream()
                .toArray(String[]::new);
        System.out.println("List转数组: " + Arrays.toString(fromList));
    }
}
