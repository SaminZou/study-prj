package com.samin.algorithm.leetcode;

import java.util.Arrays;

public class CreateTargetArray {
    public int[] createTargetArray(int[] nums, int[] index) {
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[index[i]] = nums[i];
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(
                Arrays.toString(
                        new CreateTargetArray()
                                .createTargetArray(
                                        new int[] {0, 1, 2, 3, 4}, new int[] {0, 1, 2, 2, 1})));
        System.out.println(
                Arrays.toString(
                        new CreateTargetArray()
                                .createTargetArray(
                                        new int[] {1, 2, 3, 4, 0}, new int[] {0, 1, 2, 3, 0})));
    }
}
