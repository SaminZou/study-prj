package com.samin.leetcode.algorithms;

import com.samin.leetcode.algorithms.base.model.TreeNode;

import java.util.ArrayList;

public class IsSameTree {

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(-685);
        TreeNode t3 = new TreeNode(3);
        //        t1.left = t2;
        t1.right = t3;

        TreeNode t4 = new TreeNode(1);
        TreeNode t5 = new TreeNode(0);
        TreeNode t6 = new TreeNode(3);
        t4.left = t5;
        t4.right = t6;

        System.out.println(new IsSameTree().isSameTree(t1, t4));
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        ArrayList<Integer> pVals = new ArrayList<>();
        ArrayList<Integer> qVals = new ArrayList<>();

        dfs(pVals, p);
        dfs(qVals, q);

        if (pVals.size() != qVals.size()) {
            return false;
        }

        for (int i = 0; i < pVals.size(); i++) {
            if (!pVals.get(i).equals(qVals.get(i))) {
                return false;
            }
        }

        return true;
    }

    private void dfs(ArrayList<Integer> vals, TreeNode root) {
        if (root != null) {
            vals.add(root.val);
            dfs(vals, root.left);
            dfs(vals, root.right);
        } else {
            vals.add(0);
        }
    }
}
