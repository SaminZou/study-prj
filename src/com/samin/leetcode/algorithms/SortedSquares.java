package com.samin.leetcode.algorithms;

import com.samin.leetcode.algorithms.base.util.QuickSort;

import java.util.Arrays;

public class SortedSquares {

    public static void main(String[] args) {
        System.out.println(
                Arrays.toString(new SortedSquares().sortedSquares(new int[] {-4, -1, 0, 3, 10})));
        System.out.println(
                Arrays.toString(new SortedSquares().sortedSquares(new int[] {-7, -3, 2, 3, 11})));
    }

    public int[] sortedSquares(int[] A) {
        for (int i = 0; i < A.length; i++) {
            A[i] = (int) Math.pow(A[i], 2);
        }

        QuickSort.action(A, 0, A.length - 1);
        return A;
    }
}
