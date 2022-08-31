package algorithms;

/**
 * 好数对的数目
 *
 * <p>给你一个整数数组 nums 。
 *
 * <p>如果一组数字 (i,j) 满足 nums[i] == nums[j] 且 i < j ，就可以认为这是一组 好数对 。
 *
 * <p>返回好数对的数目。
 *
 * @author samin
 * @date 2021-01-11
 */
public class NumIdenticalPairs {

    public static void main(String[] args) {
        System.out.println(new NumIdenticalPairs().numIdenticalPairs(new int[]{1, 2, 3, 1, 1, 3}));
        System.out.println(new NumIdenticalPairs().numIdenticalPairs(new int[]{1, 1, 1, 1}));
        System.out.println(new NumIdenticalPairs().numIdenticalPairs(new int[]{1, 2, 3}));
    }

    public int numIdenticalPairs(int[] nums) {
        int sum = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    sum = sum + 1;
                }
            }
        }

        return sum;
    }
}
