package algorithms;

import algorithms.base.model.TreeNode;

/**
 * 最小高度树
 *
 * @author samin
 * @date 2021-01-11
 */
public class SortedArrayToBST {

    public static void main(String[] args) {
        TreeNode res = new SortedArrayToBST().sortedArrayToBST(new int[] {-10, -3, 0, 5, 9});
        System.out.println();
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return pro(nums, 0, nums.length - 1);
    }

    private TreeNode pro(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = (right - left + 1) / 2 + left;
        TreeNode treeNode = new TreeNode(nums[mid]);
        treeNode.left = pro(nums, left, mid - 1);
        treeNode.right = pro(nums, mid + 1, right);

        return treeNode;
    }
}
