package algorithms;

import java.util.Arrays;

public class MaxSlidingWindow {

    public static void main(String[] args) {
        int[] result =
                new MaxSlidingWindow().maxSlidingWindow(new int[] {1, 3, -1, -3, 5, 3, 6, 7}, 4);
        System.out.println(Arrays.toString(result));
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) {
            return new int[] {};
        }

        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length - k + 1; i++) {
            int max = nums[i];
            for (int j = 1; j < k; j++) {
                max = Math.max(max, nums[i + j]);
            }
            result[i] = max;
        }

        return result;
    }
}
