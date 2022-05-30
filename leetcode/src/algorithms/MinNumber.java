package algorithms;

import algorithms.base.util.QuickSort;

/**
 * 把数组排成最小的数
 *
 * @author samin
 * @date 2021-01-11
 */
public class MinNumber {

    public static void main(String[] args) {
        System.out.println(new MinNumber().minNumber(new int[] {10, 2}));
        System.out.println(new MinNumber().minNumber(new int[] {3, 30, 34, 5, 9}));
    }

    public String minNumber(int[] nums) {
        QuickSort.quickSort(nums, 0, nums.length - 1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            sb.append(nums[i]);
        }
        return sb.toString();
    }
}
