package algorithms;

import algorithms.base.util.QuickSort;

/**
 * 数组拆分 I
 *
 * @author samin
 * @date 2021-01-03
 */
public class ArrayPairSum {

    public static void main(String[] args) {
        System.out.println(new ArrayPairSum().arrayPairSum(new int[]{1, 4, 3, 2}));
    }

    public int arrayPairSum(int[] nums) {
        new QuickSort().quickSort(nums, 0, nums.length - 1);

        int res = 0;
        for (int i = 0; i < nums.length; i = i + 2) {
            res = res + nums[i];
        }

        return res;
    }
}
