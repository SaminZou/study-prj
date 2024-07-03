package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和
 *
 * <p> 15.三数之和
 *
 * <p> 解题思路：排序 + 双指针
 *
 * @author samin
 * @date 2021-01-03
 */
public class ThreeSum {

    public static void main(String[] args) {
        // [[-1,-1,2],[-1,0,1]]
        System.out.println(new ThreeSum().threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
        // []
        System.out.println(new ThreeSum().threeSum(new int[]{0, 1, 1}));
        // [[0,0,0]]
        System.out.println(new ThreeSum().threeSum(new int[]{0, 0, 0}));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        // 模式识别：利用排序避免重复答案
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        for (int first = 0; first < n; ++first) {
            // 搭配模式识别使用，判断是否和上一个数不相同，旨在跳过重复的答案
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }

            int third = n - 1;
            int target = -nums[first];
            // 满足条件的两数之和
            for (int second = first + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    res.add(list);
                }
            }
        }

        return res;
    }
}
