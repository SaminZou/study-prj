package algorithms;

import algorithms.base.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的后序遍历
 *
 * @author samin
 * @date 2021-01-11
 */
public class PostorderTraversal {

    public static void main(String[] args) {}

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        pro(root, res);

        return res;
    }

    public void pro(TreeNode root, List<Integer> res) {
        if (root != null) {
            pro(root.left, res);
            pro(root.right, res);
            res.add(root.val);
        }
    }
}
