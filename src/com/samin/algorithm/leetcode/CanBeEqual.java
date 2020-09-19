package com.samin.algorithm.leetcode;

/** 最好的方式，使用桶排序 */
public class CanBeEqual {
    public boolean canBeEqual(int[] target, int[] arr) {
        quickSort(target, 0, target.length - 1);
        quickSort(arr, 0, target.length - 1);

        for (int i = 0; i < target.length; i++) {
            if (target[i] != arr[i]) {
                return false;
            }
        }

        return true;
    }

    private void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int i = left;
            int j = right;
            int index = arr[left];
            int tmp;

            while (i < j) {
                while (arr[j] >= index && i < j) {
                    j--;
                }

                while (arr[i] <= index && i < j) {
                    i++;
                }

                if (i < j) {
                    tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }

            arr[left] = arr[i];
            arr[i] = index;
            quickSort(arr, left, i - 1);
            quickSort(arr, i + 1, right);
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new CanBeEqual().canBeEqual(new int[] {1, 2, 3, 4}, new int[] {2, 4, 1, 3}));
        System.out.println(new CanBeEqual().canBeEqual(new int[] {7}, new int[] {7}));
        System.out.println(new CanBeEqual().canBeEqual(new int[] {1, 12}, new int[] {12, 1}));
        System.out.println(new CanBeEqual().canBeEqual(new int[] {3, 7, 9}, new int[] {3, 7, 11}));
        System.out.println(
                new CanBeEqual().canBeEqual(new int[] {1, 1, 1, 1, 1}, new int[] {1, 1, 1, 1, 1}));
    }
}
