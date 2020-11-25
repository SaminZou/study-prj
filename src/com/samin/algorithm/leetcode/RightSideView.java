package com.samin.algorithm.leetcode;

import com.samin.algorithm.base.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RightSideView {
    private final List<TreeNode> res = new ArrayList<>();

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        t1.left = t2;
        t1.right = t3;
        t2.right = t5;
        t3.right = t4;
        System.out.println(new RightSideView().rightSideView(t1));
    }

    public List<Integer> rightSideView(TreeNode root) {
        pro(root, 0, res);
        return res.stream().map(e -> e.val).collect(Collectors.toList());
    }

    private void pro(TreeNode root, int level, List<TreeNode> res) {
        if (root == null) {
            return;
        }

        if (res.size() == level) {
            res.add(root);
        }

        pro(root.right, level + 1, res);
        pro(root.left, level + 1, res);
    }
}
