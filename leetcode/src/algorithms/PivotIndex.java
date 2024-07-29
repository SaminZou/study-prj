package algorithms;

/**
 * 724.寻找数组的中心下标
 * <p>
 * Description: https://leetcode.cn/problems/tvdfij/
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2024-07-27
 */
public class PivotIndex {

    public static void main(String[] args) {
        // 3
        System.out.println(new PivotIndex().pivotIndexOfficial(new int[]{1, 7, 3, 6, 5, 6}));
        // -1
        System.out.println(new PivotIndex().pivotIndexOfficial(new int[]{1, 2, 3}));
        // 0
        System.out.println(new PivotIndex().pivotIndexOfficial(new int[]{2, 1, -1}));
        // 2
        System.out.println(new PivotIndex().pivotIndexOfficial(new int[]{1, -1, 2}));
    }

    public int pivotIndexOfficial(int[] nums) {
        int total = sum(0, nums.length - 1, nums);

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sum == total - nums[i] - sum) {
                return i;
            }

            sum += nums[i];
        }

        return -1;
    }

    /**
     * 暴力解法
     *
     * @param nums
     * @return
     */
    public int pivotIndex(int[] nums) {
        int total = sum(0, nums.length - 1, nums);

        for (int i = 0; i < nums.length; i++) {
            int leftSum = sum(0, i - 1, nums);
            if (leftSum == total - nums[i] - leftSum) {
                return i;
            }
        }

        return -1;
    }

    private int sum(int left, int right, int[] nums) {
        if (left < 0 || right == nums.length) {
            return 0;
        }

        int sum = 0;
        for (int i = left; i <= right; i++) {
            sum += nums[i];
        }

        return sum;
    }
}