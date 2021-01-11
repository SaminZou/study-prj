package algorithms;

import algorithms.base.model.TreeNode;

/**
 * 平衡二叉树
 *
 * @author samin
 * @date 2021-01-11
 */
public class IsBalanced {

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(3);
        TreeNode t2 = new TreeNode(9);
        TreeNode t3 = new TreeNode(20);
        t1.left = t2;
        t1.right = t3;
        TreeNode t4 = new TreeNode(15);
        TreeNode t5 = new TreeNode(7);
        t3.left = t4;
        t3.right = t5;
        System.out.println(new IsBalanced().isBalanced(t1));

        //        TreeNode t1 = new TreeNode(1);
        //        TreeNode t2 = new TreeNode(2);
        //        TreeNode t3 = new TreeNode(2);
        //        t1.left = t2;
        //        t1.right = t3;
        //        TreeNode t4 = new TreeNode(3);
        //        TreeNode t5 = new TreeNode(3);
        //        t2.left = t4;
        //        t2.right = t5;
        //        TreeNode t6 = new TreeNode(4);
        //        TreeNode t7 = new TreeNode(4);
        //        t4.left = t6;
        //        t4.right = t7;
        //
        //        System.out.println(new IsBalanced().isBalanced(t1));
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        return Math.abs(dept(root.left) - dept(root.right)) <= 1
                && isBalanced(root.left)
                && isBalanced(root.right);
    }

    public int dept(TreeNode node) {
        if (node == null) {
            return 0;
        }

        return Math.max(dept(node.left), dept(node.right)) + 1;
    }
}
