package com.samin.algorithm.leetcode;

/** 最小开始数，保证累加总是大于1 */
class MinStartValue {

    /*
    计算累加过程最小数，如果最小数小于1，求其绝对值加上1即为答案
     */
    public int minStartValue(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int sum = 0;
        int temp = 0;
        for (int num : nums) {
            sum += num;
            if (temp > sum) {
                temp = sum;
            }
        }
        if (temp >= 1) {
            return 1;
        } else {
            return -temp + 1;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[] {-3, 2, -3, 4, 2};
        int[] nums2 = new int[] {1, 2};
        int[] nums3 = new int[] {1, -2, -3};
        System.out.println(new MinStartValue().minStartValue(nums));
        System.out.println(new MinStartValue().minStartValue(nums2));
        System.out.println(new MinStartValue().minStartValue(nums3));
    }
}
