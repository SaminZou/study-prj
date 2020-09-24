package com.samin.algorithm.leetcode;

import com.samin.algorithm.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 使用DFS和BFS计算二叉树的最大深度
 */
public class MaxDepth {

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public int maxDepth2(TreeNode root) {
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

    public static void main(String[] args) {}
}
