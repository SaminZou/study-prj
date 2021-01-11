package algorithms;

import java.util.HashMap;
import java.util.Map;

/**
 * 数组中出现次数超过一半的数字 / 多数元素
 *
 * @author samin
 * @date 2021-01-11
 */
public class MajorityElement {

    public static void main(String[] args) {
        System.out.println(
                new MajorityElement().majorityElement(new int[] {1, 2, 3, 2, 2, 2, 5, 4, 2}));
        System.out.println(
                new MajorityElement()
                        .majorityElement(new int[] {1, 1, 1, 2, 10, 2, 9, 2, 5, 4, 2, 11, 3}));
    }

    public int majorityElement(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        Map<Integer, Integer> sums = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (sums.containsKey(nums[i])) {
                sums.put(nums[i], sums.get(nums[i]) + 1);
            } else {
                sums.put(nums[i], 1);
            }
        }

        int result = 0;
        int temp = 0;
        for (Integer integer : sums.keySet()) {
            if (temp < sums.get(integer)) {
                result = integer;
                temp = sums.get(integer);
            }
        }

        return temp > nums.length / 2 ? result : 0;
    }
}
