package com.samin.leetcode;

public class MinNumber {
    public String minNumber(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            sb.append(nums[i]);
        }
        return sb.toString();
    }

    private void quickSort(int[] nums, int left, int right) {
        if (left < right) {
            int i = left;
            int j = right;
            int index = nums[left];
            int tmp;

            while (i < j) {
                while ((String.valueOf(nums[j]) + index).compareTo(
                        String.valueOf(index) + nums[j]) >= 0 && i < j) {
                    j--;
                }

                while ((String.valueOf(nums[i]) + index).compareTo(
                        String.valueOf(index) + nums[i]) <= 0 && i < j) {
                    i++;
                }

                if (i < j) {
                    tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tmp;
                }
            }

            nums[left] = nums[i];
            nums[i] = index;
            quickSort(nums, left, i - 1);
            quickSort(nums, i + 1, right);
        }
    }

    public static void main(String[] args) {
        System.out.println(new MinNumber().minNumber(new int[]{10, 2}));
        System.out.println(new MinNumber().minNumber(new int[]{3, 30, 34, 5, 9}));
    }
}
