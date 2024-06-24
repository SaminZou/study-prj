package algorithms;

import algorithms.base.model.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 二叉树的后序遍历
 *
 * @author samin
 * @date 2021-01-11
 */
public class PostorderTraversal {

    public static void main(String[] args) {
        TreeNode t9 = new TreeNode(9);
        TreeNode t7 = new TreeNode(7);
        TreeNode t0 = new TreeNode(0);
        TreeNode t5 = new TreeNode(5);
        TreeNode t3 = new TreeNode(3);
        TreeNode t8 = new TreeNode(8, t7, t9);
        TreeNode t4 = new TreeNode(4, t3, t5);
        TreeNode t2 = new TreeNode(2, t0, t4);
        TreeNode t6 = new TreeNode(6, t2, t8);

        // [0, 3, 5, 4, 2, 7, 9, 8, 6]
        System.out.println(new PostorderTraversal().postorderTraversal(t6));
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        pro(root, res);

        return res;
    }

    public void pro(TreeNode root, List<Integer> res) {
        if (Objects.nonNull(root)) {
            pro(root.left, res);
            pro(root.right, res);
            res.add(root.val);
        }
    }
}
