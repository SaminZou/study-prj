package algorithms;

import java.util.HashMap;
import java.util.Map;

/**
 * 数组中重复的数字
 *
 * <p> https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/
 *
 * @author samin
 * @date 2021-01-11
 */
public class FindRepeatNumber {

    public int findRepeatNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                return num;
            }
            map.put(num, 1);
        }
        return -1;
    }
}
