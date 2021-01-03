package com.samin.leetcode.algorithms;

import com.samin.leetcode.algorithms.base.util.QuickSort;

public class GetLeastNumbers {

    public int[] getLeastNumbers(int[] arr, int k) {
        QuickSort.action(arr, 0, arr.length - 1);

        int[] result = new int[k];

        if (k >= 0) {
            System.arraycopy(arr, 0, result, 0, k);
        }

        return result;
    }
}
