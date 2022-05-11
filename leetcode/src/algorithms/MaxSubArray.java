package algorithms;

/**
 * 连续子数组的最大和 / 最大子序和
 *
 * <p>输入一个整型数组，数组里有正数也有负数
 *
 * <p>数组中的一个或连续多个整数组成一个子数组
 *
 * <p>求所有子数组的和的最大值
 *
 * <p>要求时间复杂度为O(n)
 *
 * @author samin
 * @date 2021-01-11
 */
public class MaxSubArray {

    public static void main(String[] args) {
        System.out.println(new MaxSubArray().maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    public int maxSubArray(int[] nums) {
        // 特殊入参
        if (nums.length == 0) {
            return 0;
        }

        int res = nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // 只要前面的结果是负数，就从下一个开始重新累加（隐藏原则，负数不管加负数还是加正数，一定比正数从新累加小）
            if (dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }

            res = Math.max(res, dp[i]);
        }

        return res;
    }

    // 使用原数组存储，不需要额外空间
    public int maxSubArray2(int[] nums) {
        // 特殊入参
        if (nums.length == 0) {
            return 0;
        }

        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // 只要前面的结果是负数，就从下一个开始重新累加（隐藏原则，负数不管加负数还是加正数，一定比正数从新累加小）
            if (nums[i - 1] > 0) {
                nums[i] = nums[i - 1] + nums[i];
            }
            res = Math.max(res, nums[i]);
        }

        return res;
    }
}
