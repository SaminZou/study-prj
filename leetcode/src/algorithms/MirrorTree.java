package algorithms;

import algorithms.base.model.TreeNode;

/**
 * 二叉树的镜像
 *
 * @author samin
 * @date 2021-01-11
 */
public class MirrorTree {

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(4);
        TreeNode t2 = new TreeNode(2);
        t1.left = t2;
        TreeNode t3 = new TreeNode(7);
        t1.right = t3;
        TreeNode t4 = new TreeNode(1);
        t2.left = t4;
        TreeNode t5 = new TreeNode(3);
        t2.right = t5;
        TreeNode t6 = new TreeNode(6);
        t3.left = t6;
        TreeNode t7 = new TreeNode(9);
        t3.right = t7;
        TreeNode result = new MirrorTree().mirrorTree(t1);
        System.out.println();
    }

    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode temp = root.left;
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(temp);
        return root;
    }
}
