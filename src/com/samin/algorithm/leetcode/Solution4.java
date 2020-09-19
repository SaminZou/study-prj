package com.samin.algorithm.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class Solution4 {

    public int maxDepth(TreeNode root) {
        int result = 0;

        if (root == null) {
            return result;
        }

        Queue<TreeNode> rootQueue = new LinkedList<>();
        rootQueue.add(root);
        while (rootQueue.size() > 0) {
            ++result;
            for (int i = rootQueue.size(); i > 0; i--) {
                TreeNode node = rootQueue.poll();
                if (node.left != null) {
                    rootQueue.add(node.left);
                }
                if (node.right != null) {
                    rootQueue.add(node.right);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(3);
        TreeNode t2 = new TreeNode(9);
        TreeNode t3 = new TreeNode(20);
        TreeNode t4 = new TreeNode(15);
        TreeNode t5 = new TreeNode(7);
        TreeNode t6 = new TreeNode(8);

        t1.left = t2;
        t1.right = t3;
        t3.left = t4;
        t3.right = t5;
        t5.left = t6;

        System.out.println(new Solution4().maxDepth(t1));
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
