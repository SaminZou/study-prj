package com.samin.leetcode;

import com.samin.base.TreeNode;

public class LowestCommonAncestor {
    // 适用二叉搜索树的最近公共祖先遍历
//    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//        // 使用迭代法找出最近公共节点
//        while (root != null) {
//            if (p.val < root.val && q.val < root.val) { // 在左子树的情况
//                root = root.left;
//            } else if (p.val > root.val && q.val > root.val) { // 在右子树的情况
//                root = root.right;
//            } else {
//                return root;
//            }
//        }
//
//        return root;
//    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // 1. 左右子树都不包含 p，q 节点
        if (left == null && right == null) {
            return null;
        }

        // 2. 说明最近公共祖先在右子树，先返回哪个说明哪个在偏上方
        if (left == null) {
            return right;
        }

        // 3. 说明最近公共祖先在左子树，先返回哪个说明哪个在偏上方
        if (right == null) {
            return left;
        }

        // 4. left != null && right != null
        // 说明在 root 的两边，结果即为 root
        return root;
    }

    public static void main(String[] args) {
        TreeNode t0 = new TreeNode(0);
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        TreeNode t8 = new TreeNode(8);
        t3.left = t5;
        t3.right = t1;
        t5.left = t6;
        t5.right = t2;
        t2.left = t7;
        t2.right = t4;
        t1.left = t0;
        t1.right = t8;
        TreeNode res = new LowestCommonAncestor().lowestCommonAncestor(t3, t4, t5);
        System.out.println(res.val);
    }
}
