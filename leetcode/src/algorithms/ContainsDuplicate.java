package algorithms;

import algorithms.base.util.QuickSort;

/**
 * 217 存在重复元素
 *
 * @author samin
 * @date 2022-05-30
 */
public class ContainsDuplicate {

    public static void main(String[] args) {
        // true
        System.out.println(new ContainsDuplicate().containsDuplicate(new int[]{1, 2, 3, 1}));
        // false
        System.out.println(new ContainsDuplicate().containsDuplicate(new int[]{1, 2, 3, 4}));
        // true
        System.out.println(new ContainsDuplicate().containsDuplicate(new int[]{1, 1, 1, 3, 3, 4, 3, 2, 4, 2}));
    }

    public boolean containsDuplicate(int[] nums) {
        if (nums.length == 1) {
            return false;
        }

        QuickSort.quickSort(nums, 0, nums.length - 1);

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return true;
            }
        }

        return false;
    }
}
