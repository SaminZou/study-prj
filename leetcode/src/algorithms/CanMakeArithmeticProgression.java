package algorithms;

import algorithms.base.util.QuickSort;

/**
 * 判断能否形成等差数列
 *
 * @author samin
 * @date 2021-01-11
 */
public class CanMakeArithmeticProgression {

    public static void main(String[] args) {
        System.out.println(new CanMakeArithmeticProgression().canMakeArithmeticProgression(new int[]{3, 5, 1}));
        System.out.println(new CanMakeArithmeticProgression().canMakeArithmeticProgression(new int[]{5, 3, 1}));
        System.out.println(new CanMakeArithmeticProgression().canMakeArithmeticProgression(new int[]{1, 2, 4}));
    }

    public boolean canMakeArithmeticProgression(int[] arr) {
        if (arr.length == 2) {
            return true;
        }

        new QuickSort().quickSort(arr, 0, arr.length - 1);
        int index = arr[1] - arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (i != arr.length - 1 && arr[i + 1] - arr[i] != index) {
                return false;
            }
        }

        return true;
    }
}
