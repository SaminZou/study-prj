package algorithms;

import java.util.Arrays;

/**
 * 一维数组的动态和
 *
 * @author samin
 * @date 2021-01-11
 */
public class RunningSum {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new RunningSum().runningSum(new int[] {1, 2, 3, 4})));
        System.out.println(Arrays.toString(new RunningSum().runningSum(new int[] {1, 1, 1, 1, 1})));
        System.out.println(
                Arrays.toString(new RunningSum().runningSum(new int[] {3, 1, 2, 10, 1})));
        System.out.println(Arrays.toString(new RunningSum().runningSum(new int[] {})));
    }

    public int[] runningSum(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i != 0) {
                nums[i] = nums[i] + nums[i - 1];
            }
        }

        return nums;
    }
}
