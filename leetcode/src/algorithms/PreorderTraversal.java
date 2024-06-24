package algorithms;

import algorithms.base.model.TreeNode;
import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的前序遍历
 *
 * @author samin
 * @date 2021-01-11
 */
public class PreorderTraversal {

    List<Integer> list = new ArrayList<>();

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

        // [6, 2, 0, 4, 3, 5, 8, 7, 9]
        System.out.println(new PreorderTraversal().preorderTraversal(t6));
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return list;
        }

        list.add(root.val);
        preorderTraversal(root.left);
        preorderTraversal(root.right);

        return list;
    }
}
