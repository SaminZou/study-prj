package com.samin.algorithm.leetcode;

import com.samin.algorithm.base.TreeNode;

// 完全二叉树的节点个数
public class CountNodes {

    int res = 0;

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        res += 1;
        countNodes(root.left);
        countNodes(root.right);

        return res;
    }
}
