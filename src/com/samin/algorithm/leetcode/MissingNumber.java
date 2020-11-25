package com.samin.algorithm.leetcode;

public class MissingNumber {

    public static void main(String[] args) {
        System.out.println(new MissingNumber().missingNumber(new int[] {0}));
        System.out.println(new MissingNumber().missingNumber(new int[] {0, 1, 3}));
        System.out.println(
                new MissingNumber().missingNumber(new int[] {0, 1, 2, 4, 5, 6, 7, 8, 9}));
    }

    public int missingNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i != nums[i]) {
                return i;
            }
        }

        return nums.length;
    }
}
