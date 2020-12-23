package com.samin.algorithm.leetcode;

import com.samin.algorithm.base.TreeNode;

/**
 * 完全二叉树的节点个数
 *
 * @author samin
 * @date 2020-12-23
 */
public class CountNodes {

    int res = 0;

    /** 利用前中后序遍历都可以完成统计 */
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
