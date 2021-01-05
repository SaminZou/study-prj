package algorithms;

import algorithms.base.model.TreeNode;

/**
 * 从根到叶的二进制数之和
 *
 * @author samin
 * @date 2020-12-29
 */
public class SumRootToLeaf {

    /** 存储最终结果 */
    int ans;

    public int sumRootToLeaf(TreeNode root) {
        binarySumForEach(root, 0);
        return ans;
    }

    public void binarySumForEach(TreeNode root, int binarySum) {
        if (root == null) {
            return;
        }

        // 确定为从根路径到叶子节点
        if (root.left == null && root.right == null) {
            ans += binarySum * 2 + root.val;
            return;
        }

        /// 继续进入下一个节点
        binarySumForEach(root.left, binarySum * 2 + root.val);
        binarySumForEach(root.right, binarySum * 2 + root.val);
    }
}
