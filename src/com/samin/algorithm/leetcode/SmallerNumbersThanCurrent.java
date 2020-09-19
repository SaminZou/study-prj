package com.samin.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class SmallerNumbersThanCurrent {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] temp = Arrays.copyOf(nums, nums.length);
        quickSort(nums, 0, nums.length - 1);

        HashMap<Integer, Integer> numsSeqMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            numsSeqMap.putIfAbsent(nums[i], i);
        }

        for (int i = 0; i < temp.length; i++) {
            temp[i] = numsSeqMap.get(temp[i]);
        }

        return temp;
    }

    private void quickSort(int[] nums, int left, int right) {
        if (left < right) {
            int i = left;
            int j = right;
            int index = nums[left];
            int temp;

            while (i < j) {
                while (nums[j] >= index && i < j) {
                    j--;
                }

                while (nums[i] <= index && i < j) {
                    i++;
                }

                if (i < j) {
                    temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }

            nums[left] = nums[i];
            nums[i] = index;
            quickSort(nums, left, i - 1);
            quickSort(nums, i + 1, right);
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new SmallerNumbersThanCurrent().smallerNumbersThanCurrent(
                new int[]{})));
        System.out.println(Arrays.toString(new SmallerNumbersThanCurrent().smallerNumbersThanCurrent(
                new int[]{8, 1, 2, 2, 3})));
        System.out.println(Arrays.toString(new SmallerNumbersThanCurrent().smallerNumbersThanCurrent(
                new int[]{6, 5, 4, 8})));
        System.out.println(Arrays.toString(new SmallerNumbersThanCurrent().smallerNumbersThanCurrent(
                new int[]{7, 7, 7, 7})));
    }
}
