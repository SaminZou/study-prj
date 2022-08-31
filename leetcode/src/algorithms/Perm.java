package algorithms;

import java.util.Arrays;

/**
 * 全排列
 *
 * @author samin
 * @date 2020-12-29
 */
public class Perm {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4};
        new Perm().perm(arr, 0, arr.length - 1);
    }

    public void perm(int[] nums, int start, int end) {
        if (start == end) {
            System.out.println(Arrays.toString(nums));
        } else {
            for (int i = start; i <= end; i++) {
                swap(nums, start, i);
                perm(nums, start + 1, end);
                swap(nums, start, i);
            }
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
