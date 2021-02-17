package com.samin.algorithm.leetcode;

/*
输入一个整型数组，数组里有正数也有负数。数组中的一个或连续多个整数组成一个子数组。
求所有子数组的和的最大值。
要求时间复杂度为O(n)。
 */
public class MaxSubArray {

    public int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int res = nums[0];
        int dp[] = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }

            res = Math.max(res, dp[i]);
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new MaxSubArray().maxSubArray(new int[] {}));
    }
}
