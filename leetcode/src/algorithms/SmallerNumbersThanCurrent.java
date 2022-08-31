package algorithms;

import algorithms.base.util.QuickSort;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 有多少小于当前数字的数字
 *
 * @author samin
 * @date 2021-01-03
 */
public class SmallerNumbersThanCurrent {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new SmallerNumbersThanCurrent().smallerNumbersThanCurrent(new int[]{})));
        System.out.println(
                Arrays.toString(new SmallerNumbersThanCurrent().smallerNumbersThanCurrent(new int[]{8, 1, 2, 2, 3})));
        System.out.println(
                Arrays.toString(new SmallerNumbersThanCurrent().smallerNumbersThanCurrent(new int[]{6, 5, 4, 8})));
        System.out.println(
                Arrays.toString(new SmallerNumbersThanCurrent().smallerNumbersThanCurrent(new int[]{7, 7, 7, 7})));
    }

    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] temp = Arrays.copyOf(nums, nums.length);
        QuickSort.quickSort(nums, 0, nums.length - 1);

        // 计算每个元素的排序即得出小于它所有数字的数目
        HashMap<Integer, Integer> numsSeqMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            numsSeqMap.putIfAbsent(nums[i], i);
        }

        // 计算结果
        for (int i = 0; i < temp.length; i++) {
            temp[i] = numsSeqMap.get(temp[i]);
        }

        return temp;
    }
}
