package algorithms;

import algorithms.base.util.QuickSort;

/**
 * 最小的k个数
 *
 * @author samin
 * @date 2021-01-11
 */
public class GetLeastNumbers {

    public int[] getLeastNumbers(int[] arr, int k) {
        new QuickSort().quickSort(arr, 0, arr.length - 1);

        int[] result = new int[k];

        if (k >= 0) {
            System.arraycopy(arr, 0, result, 0, k);
        }

        return result;
    }
}
