package algorithms;

import algorithms.base.util.QuickSort;

/**
 * 通过翻转子数组使两个数组相等
 *
 * <p>最好的方式，使用桶排序
 *
 * @author samin
 * @date 2021-01-11
 */
public class CanBeEqual {

    public static void main(String[] args) {
        System.out.println(new CanBeEqual().canBeEqual(new int[]{1, 2, 3, 4}, new int[]{2, 4, 1, 3}));
        System.out.println(new CanBeEqual().canBeEqual(new int[]{7}, new int[]{7}));
        System.out.println(new CanBeEqual().canBeEqual(new int[]{1, 12}, new int[]{12, 1}));
        System.out.println(new CanBeEqual().canBeEqual(new int[]{3, 7, 9}, new int[]{3, 7, 11}));
        System.out.println(new CanBeEqual().canBeEqual(new int[]{1, 1, 1, 1, 1}, new int[]{1, 1, 1, 1, 1}));
    }

    public boolean canBeEqual(int[] target, int[] arr) {
        QuickSort.quickSort(target, 0, target.length - 1);
        QuickSort.quickSort(arr, 0, target.length - 1);

        for (int i = 0; i < target.length; i++) {
            if (target[i] != arr[i]) {
                return false;
            }
        }

        return true;
    }
}
