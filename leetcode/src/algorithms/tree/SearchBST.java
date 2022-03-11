package algorithms.tree;

import algorithms.base.model.TreeNode;

/**
 * 二叉搜索树中的搜索
 *
 * @author samin
 * @date 2020-12-28
 */
public class SearchBST {

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        } else if (root.val > val) {
            return searchBST(root.left, val);
        } else if ((root.val < val)) {
            return searchBST(root.right, val);
        } else {
            return root;
        }
    }
}
