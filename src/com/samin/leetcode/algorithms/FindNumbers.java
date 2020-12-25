package com.samin.leetcode.algorithms;

public class FindNumbers {

    //    public int findNumbers(int[] nums) {
    //        int result = 0;
    //        for (int i = 0; i < nums.length; i++) {
    //            int index = 0;
    //            while (nums[i] > 0) {
    //                nums[i] = nums[i] / 10;
    //                index = index + 1;
    //            }
    //            if (index % 2 == 0) {
    //                result = result + 1;
    //            }
    //        }
    //        return result;
    //    }

    public static void main(String[] args) {
        System.out.println(new FindNumbers().findNumbers(new int[] {}));
        System.out.println(new FindNumbers().findNumbers(new int[] {12, 345, 2, 6, 7896}));
        System.out.println(new FindNumbers().findNumbers(new int[] {555, 901, 482, 1771}));
    }

    public int findNumbers(int[] nums) {
        int result = 0;

        for (int num : nums) {
            if (String.valueOf(num).length() % 2 == 0) {
                result = result + 1;
            }
        }

        return result;
    }
}
