package algorithms;

import java.util.Arrays;

// 按既定顺序创建目标数组
public class CreateTargetArray {

    public static void main(String[] args) {
        System.out.println(
                Arrays.toString(
                        new CreateTargetArray()
                                .createTargetArray2(
                                        new int[] {0, 1, 2, 3, 4}, new int[] {0, 1, 2, 2, 1})));

        System.out.println(
                Arrays.toString(
                        new CreateTargetArray()
                                .createTargetArray2(
                                        new int[] {1, 2, 3, 4, 0}, new int[] {0, 1, 2, 3, 0})));

        // [0,4,1,3,2]
        System.out.println(
                Arrays.toString(
                        new CreateTargetArray()
                                .createTargetArray(
                                        new int[] {0, 1, 2, 3, 4}, new int[] {0, 1, 2, 2, 1})));

        // [0,1,2,3,4]
        System.out.println(
                Arrays.toString(
                        new CreateTargetArray()
                                .createTargetArray(
                                        new int[] {1, 2, 3, 4, 0}, new int[] {0, 1, 2, 3, 0})));
    }

    // 这道题是插入
    public int[] createTargetArray(int[] nums, int[] index) {
        int[] target = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            // 顺序插入的情况
            if (i == index[i]) {
                target[index[i]] = nums[i];
                // 先后挪，再插入
            } else {
                System.arraycopy(target, index[i], target, index[i] + 1, i - index[i]);
                target[index[i]] = nums[i];
            }
        }

        return target;
    }

    // 这题是非插入
    public int[] createTargetArray2(int[] nums, int[] index) {
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[index[i]] = nums[i];
        }
        return result;
    }
}
