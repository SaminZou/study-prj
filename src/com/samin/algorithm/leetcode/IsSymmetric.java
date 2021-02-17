package com.samin.leetcode;

public class IsSymmetric {
    public boolean isSymmetric(TreeNode root) {
        return isSymmetric(root, root);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
        // 包含了空树的情况
        // 同时为空才能保证对称
        if (left == null && right == null) {
            return true;
        }

        // 其中有一个为空的情况，即不对称
        if (left == null || right == null) {
            return false;
        }

        return left.val == right.val &&
                isSymmetric(left.left, right.right) &&
                isSymmetric(left.right, right.left);
    }

    public static void main(String[] args) {

    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
