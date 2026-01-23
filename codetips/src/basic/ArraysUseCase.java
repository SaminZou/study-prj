package basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Arrays.asList() 将数组转换为集合后，底层其实还是数组，无法使用 add/remove/clear 方法
 *
 * <p>体现的是适配器模式，只是转换接口，后台的数据仍是数组
 *
 * @author samin
 * @date 2020-12-31
 */
public class ArraysUseCase {

    public static void main(String[] args) {
        System.out.println("=== Arrays.asLList() 使用陷阱示例 ===\n");

        demonstratePrimitiveArrayIssue();
        demonstrateFixedSizeListIssue();
        demonstrateCorrectUsage();
    }

    /**
     * 演示问题1：基本类型数组被当作一个元素处理
     */
    private static void demonstratePrimitiveArrayIssue() {
        System.out.println("1. 基本类型数组的问题：");
        int[] myArray = {1, 2, 3};
        List<int[]> myList = Arrays.asList(myArray);

        // myList 只有一个元素，就是整个 myArray 数组
        System.out.println("   List 大小: " + myList.size());  // 输出: 1
        System.out.println("   myList.get(0): " + Arrays.toString(myList.get(0)));  // 输出: [1, 2, 3]

        // 这会抛出 IndexOutOfBoundsException，因为 list 只有 1 个元素
        try {
            System.out.println("   myList.get(1): " + myList.get(1));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("   抛出 IndexOutOfBoundsException: 因为基本类型数组被当做一个元素");
        }

        // 解决方法：使用包装类型
        Integer[] myArray2 = {1, 2, 3};
        List<Integer> myList2 = Arrays.asList(myArray2);
        System.out.println("   使用 Integer[] 转换后的大小: " + myList2.size());  // 输出: 3
        System.out.println();
    }

    /**
     * 演示问题2：返回的是固定大小的 List，不支持增删操作
     */
    private static void demonstrateFixedSizeListIssue() {
        System.out.println("2. 固定大小 List 的问题：");
        Integer[] myArray = {1, 2, 3};
        List<Integer> myList = Arrays.asList(myArray);

        System.out.println("   实际类型: " + myList.getClass().getName());
        System.out.println("   (" + myList.getClass().getSimpleName() + " 是 Arrays 的内部类，不是 ArrayList)");

        // 不支持添加操作
        try {
            myList.add(4);
        } catch (UnsupportedOperationException e) {
            System.out.println("   抛出 UnsupportedOperationException: 不支持 add 操作");
        }

        // 不支持删除操作
        try {
            myList.remove(0);
        } catch (UnsupportedOperationException e) {
            System.out.println("   抛出 UnsupportedOperationException: 不支持 remove 操作");
        }
        System.out.println();
    }

    /**
     * 演示正确的数组转 List 方法
     */
    private static void demonstrateCorrectUsage() {
        System.out.println("3. 正确的数组转 List 方法：");

        // 方法1：使用 ArrayList 包装（推荐）
        List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 2, 3));
        list1.add(4);
        list1.remove(0);
        System.out.println("   方法1 - new ArrayList<>(Arrays.asList()): " + list1);

        // 方法2：Stream API（推荐，支持基本类型）
        Integer[] array2 = {1, 2, 3};
        List<Integer> list2 = Arrays.stream(array2).collect(Collectors.toList());
        list2.add(4);
        System.out.println("   方法2 - Stream (包装类型): " + list2);

        // 基本类型数组转换
        int[] array3 = {1, 2, 3};
        List<Integer> list3 = Arrays.stream(array3).boxed().collect(Collectors.toList());
        list3.add(4);
        System.out.println("   方法3 - Stream (基本类型): " + list3);

        // 方法4：Java 9+ List.of()（注意：返回不可变列表）
        List<Integer> list4 = List.of(1, 2, 3);
        System.out.println("   方法4 - List.of(): " + list4 + " (不可变)");

        // 如果需要可变列表，可以再包装
        List<Integer> list5 = new ArrayList<>(List.of(1, 2, 3));
        list5.add(4);
        System.out.println("   方法4' - new ArrayList<>(List.of()): " + list5 + " (可变)");
    }
}
