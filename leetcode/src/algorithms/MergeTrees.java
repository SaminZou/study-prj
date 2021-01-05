package algorithms;

import algorithms.base.model.TreeNode;

public class MergeTrees {

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(5);
        t1.left = t3;
        t1.right = t2;
        t3.left = t4;

        TreeNode t21 = new TreeNode(2);
        TreeNode t22 = new TreeNode(1);
        TreeNode t23 = new TreeNode(3);
        TreeNode t24 = new TreeNode(4);
        TreeNode t25 = new TreeNode(7);
        t21.left = t22;
        t21.right = t23;
        t22.right = t24;
        t23.right = t25;

        TreeNode res = new MergeTrees().mergeTrees(null, null);
        System.out.println(res);
    }

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 != null) {
            t1 = new TreeNode(0);
            t1.val = t1.val + t2.val;
        } else if (t1 != null && t2 != null) {
            t1.val = t1.val + t2.val;
        }

        if (t2 != null && t2.left != null) {
            t1.left = mergeTrees(t1.left, t2.left);
        }

        if (t2 != null && t2.right != null) {
            t1.right = mergeTrees(t1.right, t2.right);
        }

        return t1;
    }
}
