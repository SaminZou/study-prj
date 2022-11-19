package algorithms;

import java.util.Arrays;

/**
 * 移除元素 https://leetcode-cn.com/problems/remove-element/
 *
 * @author samin
 * @date 2021-01-11
 */
public class RemoveElement {

    public static void main(String[] args) {
        // 2,[2,2,...]
        int[] arr1 = new int[]{3, 2, 2, 3};
        System.out.println(new RemoveElement().removeElement(arr1, 3));
        System.out.println(Arrays.toString(arr1));

        // 5,[0,1,4,0,3,...]
        int[] arr2 = new int[]{0, 1, 2, 2, 3, 0, 4, 2};
        System.out.println(new RemoveElement().removeElement(arr2, 2));
        System.out.println(Arrays.toString(arr2));
    }

    public int removeElement(int[] nums, int val) {
        if (nums.length == 0) {
            return 0;
        }

        // 索引
        int result = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[result] = nums[i];
                result += 1;
            }
        }

        return result;
    }
}