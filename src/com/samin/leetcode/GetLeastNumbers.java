package com.samin.leetcode;

import java.util.Arrays;

public class GetLeastNumbers {
    public int[] getLeastNumbers(int[] arr, int k) {
        qucikSort(arr, 0, arr.length - 1);

        int[] result = new int[k];

        if (k >= 0) {
            System.arraycopy(arr, 0, result, 0, k);
        }

        return result;
    }

    /*
    快速排序
     */
    private void qucikSort(int[] arr, int left, int right) {
        if (left < right) {
            int i = left;
            int j = right;
            int index = arr[left];
            int temp;

            while (i != j) {
                while (arr[j] >= index && i < j) {
                    j--;
                }

                while (arr[i] <= index && i < j) {
                    i++;
                }

                if (i < j) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }

            arr[left] = arr[i];
            arr[i] = index;
            qucikSort(arr, left, i - 1);
            qucikSort(arr, i + 1, right);

        }
    }

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(new GetLeastNumbers().getLeastNumbers(new int[]{3, 2, 1}, 2)));
//        System.out.println(Arrays.toString(new GetLeastNumbers().getLeastNumbers(new int[]{0, 1, 2, 1}, 1)));
//        System.out.println(Arrays.toString(new GetLeastNumbers().getLeastNumbers(new int[]{0, 1, 1, 1, 4, 5, 3, 7, 7, 8, 10, 2, 7, 8, 0, 5, 2, 16, 12, 1, 19, 15, 5, 18, 2, 2, 22, 15, 8, 22, 17, 6, 22, 6, 22, 26, 32, 8, 10, 11, 2, 26, 9, 12, 9, 7, 28, 33, 20, 7, 2, 17, 44, 3, 52, 27, 2, 23, 19, 56, 56, 58, 36, 31, 1, 19, 19, 6, 65, 49, 27, 63, 29, 1, 69, 47, 56, 61, 40, 43, 10, 71, 60, 66, 42, 44, 10, 12, 83, 69, 73, 2, 65, 93, 92, 47, 35, 39, 13, 75},
//                75)));
//        System.out.println(Arrays.toString(new GetLeastNumbers().getLeastNumbers(new int[]{0, 0, 1, 2, 4, 2, 2, 3, 1, 4}, 8)));
        int[] a = new int[]{0, 0, 1, 2, 4, 2, 2, 3, 1, 4};
        new GetLeastNumbers().qucikSort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }
}
