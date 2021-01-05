package algorithms;

import java.util.HashMap;

/**
 * 两数之和
 *
 * @author samin
 * @date 2021-01-04
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] result1 = new TwoSum().twoSum(new int[] {2, 7, 11, 15}, 9);
        int[] result2 = new TwoSum().twoSum(new int[] {10, 26, 30, 31, 47, 60}, 40);
        System.out.println(result1);
        System.out.println(result2);
        System.out.println();
    }

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> cpMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            Integer cp = target - nums[i];
            if (cpMap.containsKey(cp)) {
                return new int[] {cp, nums[i]};
                // 有题目是返回索引，用以下结果返回
                // return new int[]{cpMap.get(cp), i};
            }
            cpMap.put(nums[i], i);
        }

        return new int[] {};
    }

    /**
     * 自己的解法
     *
     * @param numbers 数组
     * @param target 目标数
     * @return 结果
     */
    public int[] twoSum2(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;

        while (i < j) {
            if (numbers[i] + numbers[j] > target) {
                j--;
            }

            if (numbers[i] + numbers[j] < target) {
                i++;
            }

            if (numbers[i] + numbers[j] == target) {
                return new int[] {i + 1, j + 1};
            }
        }

        return new int[] {};
    }
}
