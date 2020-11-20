package com.samin.algorithm.leetcode;

import com.samin.algorithm.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/** 二叉树的前序遍历 */
public class PreorderTraversal {

    List<Integer> list = new ArrayList<>();

    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return list;
        }

        list.add(root.val);
        preorderTraversal(root.left);
        preorderTraversal(root.right);

        return list;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);

        t1.right = t2;
        t2.left = t3;

        System.out.println(new PreorderTraversal().preorderTraversal(t1));
    }
}
