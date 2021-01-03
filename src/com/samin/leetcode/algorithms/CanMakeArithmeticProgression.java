package com.samin.leetcode.algorithms;

import com.samin.leetcode.algorithms.base.util.QuickSort;

public class CanMakeArithmeticProgression {

    public static void main(String[] args) {
        System.out.println(
                new CanMakeArithmeticProgression()
                        .canMakeArithmeticProgression(new int[] {3, 5, 1}));
        System.out.println(
                new CanMakeArithmeticProgression()
                        .canMakeArithmeticProgression(new int[] {5, 3, 1}));
        System.out.println(
                new CanMakeArithmeticProgression()
                        .canMakeArithmeticProgression(new int[] {1, 2, 4}));
    }

    public boolean canMakeArithmeticProgression(int[] arr) {
        if (arr.length == 2) {
            return true;
        }

        QuickSort.action(arr, 0, arr.length - 1);
        int index = arr[1] - arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (i != arr.length - 1 && arr[i + 1] - arr[i] != index) {
                return false;
            }
        }

        return true;
    }
}
