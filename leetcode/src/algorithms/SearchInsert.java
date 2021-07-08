package algorithms;

/**
 * 搜索插入位置
 *
 * @author samin
 * @date 2021-07-08
 */
public class SearchInsert {

    public static void main(String[] args) {
        // 2
        System.out.println(new SearchInsert().searchInsert(new int[]{1, 3, 5, 6}, 5));
        // 1
        System.out.println(new SearchInsert().searchInsert(new int[]{1, 3, 5, 6}, 2));
        // 4
        System.out.println(new SearchInsert().searchInsert(new int[]{1, 3, 5, 6}, 7));
        // 0
        System.out.println(new SearchInsert().searchInsert(new int[]{1, 3, 5, 6}, 0));
        // 3
        System.out.println(new SearchInsert().searchInsert(new int[]{1, 3, 5, 7}, 6));
    }

    public int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (target <= nums[i]) {
                return i;
            }
        }

        return nums.length;
    }
}
