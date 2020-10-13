package com.samin.algorithm.leetcode;

import com.samin.algorithm.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class GetMinimumDifference {

    List<Integer> arrList = new ArrayList<>();

    public int getMinimumDifference(TreeNode root) {
        // 获取中序遍历结果，递增
        getMid(root);

        int[] arrs = new int[arrList.size() - 1];
        // 计算两数之间差
        for (int i = 0; i < arrList.size() - 1; i++) {
            arrs[i] = arrList.get(i + 1) - arrList.get(i);
        }

        // 排序输出结果
        quickSort(arrs, 0, arrs.length - 1);

        return arrs[0];
    }

    public void getMid(TreeNode root) {
        if (root != null) {
            getMid(root.left);
            arrList.add(root.val);
            getMid(root.right);
        }
    }

    public void quickSort(int[] arrs, int left, int right) {
        if (left < right) {
            int i = left;
            int j = right;
            int index = arrs[left];
            int tmp;

            while (i < j) {
                while (index <= arrs[j] && i < j) {
                    j--;
                }

                while (index >= arrs[i] && i < j) {
                    i++;
                }

                if (i < j) {
                    tmp = arrs[i];
                    arrs[i] = arrs[j];
                    arrs[j] = tmp;
                }
            }

            arrs[left] = arrs[i];
            arrs[i] = index;
            quickSort(arrs, left, i - 1);
            quickSort(arrs, i + 1, right);
        }
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(3);
        TreeNode t3 = new TreeNode(2);
        t1.right = t2;
        t2.left = t3;

        System.out.println(new GetMinimumDifference().getMinimumDifference(t1));
    }
}
