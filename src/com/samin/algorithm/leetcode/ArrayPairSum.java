package com.samin.algorithm.leetcode;

public class ArrayPairSum {

    public static void main(String[] args) {
        System.out.println(new ArrayPairSum().arrayPairSum(new int[] {1, 4, 3, 2}));
    }

    public int arrayPairSum(int[] nums) {
        quickSort(nums, 0, nums.length - 1);

        int res = 0;
        for (int i = 0; i < nums.length; i = i + 2) {
            res = res + nums[i];
        }

        return res;
    }

    private void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int i = left;
            int j = right;
            int index = arr[left];
            int tmp;

            while (i < j) {
                while (arr[j] >= index && i < j) {
                    j--;
                }

                while (arr[i] <= index && i < j) {
                    i++;
                }

                if (i < j) {
                    tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }

            arr[left] = arr[i];
            arr[i] = index;
            quickSort(arr, left, i - 1);
            quickSort(arr, i + 1, right);
        }
    }
}
