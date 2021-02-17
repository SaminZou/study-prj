package com.samin.leetcode;

public class Solution22 {
    /*
    解法1
     */
//    public int[] exchange(int[] nums) {
//        List<Integer> oddList = new ArrayList<>();
//        List<Integer> evenList = new ArrayList<>();
//
//        for (int ele : nums) {
//            if (ele % 2 == 0) {
//                evenList.add(ele);
//            } else {
//                oddList.add(ele);
//            }
//        }
//
//        oddList.addAll(evenList);
//
//        return oddList.stream().mapToInt(e -> e).toArray();
//    }

    /*
    双指针解法
     */
    public int[] exchange(int[] nums) {
        int index = 0;
        int endIndex = nums.length - 1;

        while (index < endIndex) {
            if (nums[index] % 2 == 0 && nums[endIndex] % 2 != 0) {
                int temp = nums[index];
                nums[index] = nums[endIndex];
                nums[endIndex] = temp;
            }

            if (nums[index] % 2 != 0) {
                index++;
            }

            if (nums[endIndex] % 2 == 0) {
                endIndex--;
            }
        }

        return nums;
    }

    public static void main(String[] args) {
        int[] result = new Solution22().exchange(new int[]{1});
        for (int ele : result) {
            System.out.print(ele + " ");
        }
    }
}
