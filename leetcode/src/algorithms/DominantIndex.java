package algorithms;

/**
 * 至少是其他数字两倍的最大数
 *
 * @author samin
 * @date 2021-01-04
 */
public class DominantIndex {

    public int dominantIndex(int[] nums) {
        int index = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[index] < nums[i]) {
                index = i;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (index != i && nums[i] * 2 > nums[index]) {
                return -1;
            }
        }

        return index;
    }

    public static void main(String[] args) {
        // 1
        System.out.println(new DominantIndex().dominantIndex(new int[] {3, 6, 1, 0}));
        // -1
        System.out.println(new DominantIndex().dominantIndex(new int[] {1, 2, 3, 4}));
        // 3

        System.out.println(new DominantIndex().dominantIndex(new int[] {0, 0, 0, 1}));
    }
}
