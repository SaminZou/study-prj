package com.samin.algorithm.leetcode;

import java.util.Arrays;

public class RunningSum {

    public int[] runningSum(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i != 0) {
                nums[i] = nums[i] + nums[i - 1];
            }
        }

        return nums;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new RunningSum().runningSum(new int[] {1, 2, 3, 4})));
        System.out.println(Arrays.toString(new RunningSum().runningSum(new int[] {1, 1, 1, 1, 1})));
        System.out.println(
                Arrays.toString(new RunningSum().runningSum(new int[] {3, 1, 2, 10, 1})));
        System.out.println(Arrays.toString(new RunningSum().runningSum(new int[] {})));
    }
}
