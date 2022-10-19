package algorithms;

import algorithms.base.util.QuickSort;

/**
 * 通过翻转子数组使两个数组相等
 *
 * <p> 最好的方式，使用桶排序
 *
 * @author samin
 * @date 2021-01-11
 */
public class CanBeEqual {

    public static void main(String[] args) {
        // true
        System.out.println(new CanBeEqual().bucket(new int[]{1, 2, 3, 4}, new int[]{2, 4, 1, 3}));
        // true
        System.out.println(new CanBeEqual().bucket(new int[]{7}, new int[]{7}));
        // true
        System.out.println(new CanBeEqual().bucket(new int[]{1, 12}, new int[]{12, 1}));
        // false
        System.out.println(new CanBeEqual().bucket(new int[]{3, 7, 9}, new int[]{3, 7, 11}));
        // true
        System.out.println(new CanBeEqual().bucket(new int[]{1, 1, 1, 1, 1}, new int[]{1, 1, 1, 1, 1}));
        // false
        System.out.println(new CanBeEqual().bucket(new int[]{1, 2, 2, 3}, new int[]{1, 1, 2, 3}));
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

    public boolean bucket(int[] target, int[] arr) {
        if (target.length != arr.length) {
            return false;
        }

        int[] bucket = new int[1001];
        for (int i = 0; i < target.length; i++) {
            bucket[target[i]] += 1;
            bucket[arr[i]] -= 1;
        }

        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] != 0) {
                return false;
            }
        }

        return true;
    }
}