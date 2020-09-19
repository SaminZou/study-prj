package com.samin.algorithm.leetcode;

import java.util.Arrays;

public class SortedSquares {

    public int[] sortedSquares(int[] A) {
        for (int i = 0; i < A.length; i++) {
            A[i] = (int) Math.pow(A[i], 2);
        }

        quickSort(A, 0, A.length - 1);
        return A;
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
                Arrays.toString(new SortedSquares().sortedSquares(new int[] {-4, -1, 0, 3, 10})));
        System.out.println(
                Arrays.toString(new SortedSquares().sortedSquares(new int[] {-7, -3, 2, 3, 11})));
    }
}
