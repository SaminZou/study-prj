package com.samin.algorithm.leetcode;

import java.util.Arrays;

public class TwoSum2 {

    //    public int[] twoSum(int[] numbers, int target) {
    //        HashMap<Integer, Integer> tmpMap = new HashMap<>();
    //
    //        for (int i = 0; i < numbers.length; i++) {
    //            if (tmpMap.containsKey(target - numbers[i])) {
    //                return new int[]{tmpMap.get(target - numbers[i]) + 1, i + 1};
    //            } else {
    //                tmpMap.put(numbers[i], i);
    //            }
    //        }
    //
    //        return new int[0];
    //    }

    public int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;
        //        while (i < numbers.length - 1 && j > 0) {
        while (i < j) {
            if (numbers[i] + numbers[j] > target) {
                j--;
            }

            if (numbers[i] + numbers[j] < target) {
                i++;
            }

            if (numbers[i] + numbers[j] == target) {
                return new int[] {i + 1, j + 1};
            }
        }

        return new int[] {};
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new TwoSum2().twoSum(new int[] {2, 7, 11, 15}, 9)));
        System.out.println(
                Arrays.toString(new TwoSum2().twoSum(new int[] {10, 26, 30, 31, 47, 60}, 40)));
    }
}
