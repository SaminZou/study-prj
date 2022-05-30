package algorithms;

import algorithms.base.util.QuickSort;

/**
 * 合并两个有序数组
 *
 * @author samin
 * @date 2021-06-26
 */
public class Merge {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (m == 0 && n == 0) {
            return;
        }

        if (n >= 0) {
            System.arraycopy(nums2, 0, nums1, m, n);
        }

        QuickSort.quickSort(nums1, 0, m + n - 1);
    }
}
