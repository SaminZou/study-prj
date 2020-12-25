package com.samin.leetcode.algorithms;

import com.samin.leetcode.algorithms.base.TreeNode;

/** 此题最佳解是中序遍历逆排序 */
public class KthLargest {

    public static void main(String[] args) {
        //        TreeNode t1 = new TreeNode(1);
        //        TreeNode t2 = new TreeNode(2);
        //        TreeNode t3 = new TreeNode(3);
        //        TreeNode t4 = new TreeNode(4);
        //        t3.left = t1;
        //        t3.right = t4;
        //        t1.right = t2;
        //        System.out.println(new KthLargest().kthLargest(t3, 1));

        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        t5.left = t3;
        t3.left = t2;
        t3.right = t4;
        t2.left = t1;
        t5.right = t6;
        System.out.println(new KthLargest().kthLargest(t5, 3));
    }

    public int kthLargest(TreeNode root, int k) {
        int res = 0;

        for (; k > 0; k--) {
            res = max(root);
            root = remove(root, res);
        }

        return res;
    }

    private TreeNode remove(TreeNode root, int val) {
        if (root == null) {
            return null;
        }

        if (val < root.val) {
            root.left = remove(root.left, val);
            return root;
        }

        if (val > root.val) {
            root.right = remove(root.right, val);
            return root;
        }

        if (root.left == null && root.right == null) {
            return null;
        }

        if (root.left == null) {
            return root.right;
        }

        if (root.right == null) {
            return root.left;
        }

        int min = min(root.right);
        root.val = min;
        root.right = remove(root.right, min);

        return root;
    }

    private int min(TreeNode root) {
        if (root == null) {
            return -1;
        }

        TreeNode tmp = root;
        while (tmp.left != null) {
            tmp = tmp.left;
        }

        return tmp.val;
    }

    private int max(TreeNode root) {
        if (root == null) {
            return -1;
        }

        TreeNode tmp = root;
        while (tmp.right != null) {
            tmp = tmp.right;
        }

        return tmp.val;
    }
}
