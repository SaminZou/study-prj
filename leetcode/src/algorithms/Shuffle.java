package algorithms;

import java.util.Arrays;

/**
 * 重新排列数组
 *
 * @author samin
 * @date 2021-01-11
 */
public class Shuffle {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Shuffle().shuffle(new int[]{1, 1, 2, 2}, 2)));
        System.out.println(Arrays.toString(new Shuffle().shuffle(new int[]{1, 2, 3, 4, 4, 3, 2, 1}, 4)));
        System.out.println(Arrays.toString(new Shuffle().shuffle(new int[]{2, 5, 1, 3, 4, 7}, 3)));
    }

    public int[] shuffle(int[] nums, int n) {
        int[] result = new int[2 * n];

        for (int i = 0; i < 2 * n; i++) {
            if (i % 2 == 0) {
                result[i] = nums[i / 2];
            } else {
                result[i] = nums[n + (i / 2)];
            }
        }

        return result;
    }
}
