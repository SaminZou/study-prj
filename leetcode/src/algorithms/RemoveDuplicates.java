package algorithms;

/**
 * * 删除排序数组中的重复项 * *
 *
 * <p>给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。 * *
 *
 * <p>不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 * @author samin
 * @date 2021-01-11
 */
public class RemoveDuplicates {

    public static void main(String[] args) {
        System.out.println(new RemoveDuplicates().removeDuplicates(new int[] {1, 1, 2}));
    }

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int cashNum = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                cashNum += 1;
                continue;
            }

            if (cashNum > 0) {
                nums[i - cashNum] = nums[i];
            }
        }

        return nums.length - cashNum;
    }
}
