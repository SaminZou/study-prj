package algorithms;

/**
 * 只出现一次的数字
 *
 * @author samin
 * @date 2021-07-07
 */
public class SingleNumber_2 {

    public static void main(String[] args) {
        // 1
        System.out.println(new SingleNumber_2().singleNumber(new int[]{2, 2, 1}));
        // 4
        System.out.println(new SingleNumber_2().singleNumber2(new int[]{4, 1, 2, 1, 2}));
        // -1
        System.out.println(new SingleNumber_2().singleNumber(new int[]{}));
    }

    public int singleNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i == nums.length - 1) {
                return nums[i];
            }

            for (int j = 0; j < nums.length; j++) {
                if (i != j && nums[i] == nums[j]) {
                    break;
                }

                if (j == nums.length - 1) {
                    return nums[i];
                }
            }
        }

        return -1;
    }

    /**
     * 官方解题思路
     *
     * @param nums 输入数组
     * @return 结果
     */
    public int singleNumber2(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }
}
