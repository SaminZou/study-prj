package algorithms;

import algorithms.base.model.TreeNode;
import algorithms.base.util.QuickSort;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉搜索树的最小绝对差
 *
 * @author samin
 * @date 2021-01-11
 */
public class GetMinimumDifference {

    List<Integer> arrList = new ArrayList<>();

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(3);
        TreeNode t3 = new TreeNode(2);
        t1.right = t2;
        t2.left = t3;

        System.out.println(new GetMinimumDifference().getMinimumDifference(t1));
    }

    public int getMinimumDifference(TreeNode root) {
        // 获取中序遍历结果，递增
        getMid(root);

        int[] arrs = new int[arrList.size() - 1];
        // 计算两数之间差
        for (int i = 0; i < arrList.size() - 1; i++) {
            arrs[i] = arrList.get(i + 1) - arrList.get(i);
        }

        // 排序输出结果
        QuickSort.action(arrs, 0, arrs.length - 1);

        return arrs[0];
    }

    public void getMid(TreeNode root) {
        if (root != null) {
            getMid(root.left);
            arrList.add(root.val);
            getMid(root.right);
        }
    }
}
