package algorithms;

/**
 * 172. 统计目标成绩的出现次数
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 *
 * <p>
 * Description: https://leetcode.cn/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/description/
 * https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/description/
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2024-07-27
 */
public class CountTarget {

    public static void main(String[] args) {
        // 3
        System.out.println(new CountTarget().countTarget(new int[]{2, 2, 3, 4, 4, 4, 5, 6, 6, 8}, 4));
        // 0
        System.out.println(new CountTarget().countTarget(new int[]{1, 2, 3, 5, 7, 9}, 6));
    }

    public int countTarget(int[] scores, int target) {
        if (scores.length == 0) {
            return 0;
        }

        int count = 0;
        int last = 0;
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] == target) {
                count += 1;
            }

            if (count != 0 && count == last) {
                return count;
            }

            last = count;
        }

        return count;
    }

    // TODO
    public int[] searchRange(int[] nums, int target) {
        return null;
    }
}