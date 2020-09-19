package com.samin.algorithm.leetcode;

import java.util.HashMap;

public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> cpMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            Integer cp = target - nums[i];
            if (cpMap.containsKey(cp)) {
                return new int[] {cp, nums[i]};
                // 有题目是返回索引，用以下结果返回
                // return new int[]{cpMap.get(cp), i};
            }
            cpMap.put(nums[i], i);
        }

        return new int[] {};
    }

    public static void main(String[] args) {
        int[] result1 = new TwoSum().twoSum(new int[] {2, 7, 11, 15}, 9);
        int[] result2 = new TwoSum().twoSum(new int[] {10, 26, 30, 31, 47, 60}, 40);
        System.out.println(result1);
        System.out.println(result2);
        System.out.println();
    }
}
