package com.samin.algorithm.leetcode;

import com.samin.algorithm.base.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Soution2 {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (root == null) {
            return resultList;
        }

        Queue<TreeNode> rootContainer = new LinkedList<>();
        rootContainer.add(root);
        List<Integer> temp;
        while (!rootContainer.isEmpty()) {
            temp = new ArrayList<>();
            int size = rootContainer.size();
            for (int i = 0; i < size; i++) { // 遍历把本层输出完毕的同时，加入全部下一层
                TreeNode ele = rootContainer.poll();
                temp.add(ele.val);
                if (ele.left != null) {
                    rootContainer.add(ele.left);
                }
                if (ele.right != null) {
                    rootContainer.add(ele.right);
                }
            }
            resultList.add(temp);
        }

        return resultList;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(3);
        TreeNode t2 = new TreeNode(9);
        TreeNode t3 = new TreeNode(20);
        TreeNode t4 = new TreeNode(15);
        TreeNode t5 = new TreeNode(7);
        t1.left = t2;
        t1.right = t3;
        t3.left = t4;
        t3.right = t5;
        System.out.println(new Soution2().levelOrder(t1));
    }
}
