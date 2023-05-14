package algorithms;

import algorithms.base.util.QuickSort;
import java.util.Arrays;

/**
 * 有序数组的平方
 *
 * @author samin
 * @date 2021-01-11
 */
public class SortedSquares {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new SortedSquares().sortedSquares(new int[]{-4, -1, 0, 3, 10})));
        System.out.println(Arrays.toString(new SortedSquares().sortedSquares(new int[]{-7, -3, 2, 3, 11})));
    }

    public int[] sortedSquares(int[] A) {
        for (int i = 0; i < A.length; i++) {
            A[i] = (int) Math.pow(A[i], 2);
        }

        new QuickSort().quickSort(A, 0, A.length - 1);
        return A;
    }
}
