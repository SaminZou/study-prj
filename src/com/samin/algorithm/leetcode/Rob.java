package com.samin.algorithm.leetcode;

import com.samin.algorithm.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Rob {

    private int oddPrice = 0;
    private int evenPrice = 0;

    public static void main(String[] args) {
        //        TreeNode t1 = new TreeNode(3);
        //        TreeNode t2 = new TreeNode(2);
        //        TreeNode t3 = new TreeNode(3);
        //        TreeNode t4 = new TreeNode(3);
        //        TreeNode t5 = new TreeNode(1);
        //        t1.left = t2;
        //        t1.right = t3;
        //        t2.right = t4;
        //        t3.right = t5;

        //        TreeNode t1 = new TreeNode(3);
        //        TreeNode t2 = new TreeNode(4);
        //        TreeNode t3 = new TreeNode(5);
        //        TreeNode t4 = new TreeNode(1);
        //        TreeNode t5 = new TreeNode(3);
        //        TreeNode t6 = new TreeNode(1);
        //        t1.left = t2;
        //        t1.right = t3;
        //        t2.left = t4;
        //        t2.right = t5;
        //        t3.right = t6;

        TreeNode t1 = new TreeNode(4);
        TreeNode t2 = new TreeNode(1);
        TreeNode t3 = new TreeNode(2);
        TreeNode t4 = new TreeNode(3);
        t1.left = t2;
        t2.left = t3;
        t3.left = t4;

        System.out.println(new Rob().rob(t1));
    }

    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        boolean isOdd = true;
        while (queue.size() != 0) {
            for (int i = queue.size(); i > 0; i--) {
                TreeNode tmp = queue.poll();

                if (isOdd) {
                    oddPrice = oddPrice + tmp.val;
                } else {
                    evenPrice = evenPrice + tmp.val;
                }

                if (tmp.left != null) {
                    queue.add(tmp.left);
                }
                if (tmp.right != null) {
                    queue.add(tmp.right);
                }
            }

            isOdd = !isOdd;
        }

        return Math.max(oddPrice, evenPrice);
    }
}
