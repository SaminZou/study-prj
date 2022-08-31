package algorithms;

import algorithms.base.model.TreeNode;
import java.util.LinkedList;
import java.util.List;

/**
 * 不同的二叉搜索树 II
 *
 * @author samin
 * @date 2021-01-11
 */
public class GenerateTrees {

    public static void main(String[] args) {
        List<TreeNode> linkedList = new GenerateTrees().generateTrees(3);
        System.out.println(linkedList);
    }

    public List<TreeNode> generateTrees(int n) {
        // 因为是是1开始，所以 '0' 条件为空值
        if (n == 0) {
            return new LinkedList<>();
        }

        return generateTrees(1, n);
    }
    //    public List<TreeNode> generateTrees(int n) {
    //        if (n == 0) {
    //            return new LinkedList<>();
    //        }
    //
    //        return generate(1, n);
    //    }
    //
    //    private List<TreeNode> generate(int start, int end) {
    //        List<TreeNode> res = new LinkedList<>();
    //
    //        if (start > end) {
    //            res.add(null);
    //            return res;
    //        }
    //
    //        for (int i = start; i <= end; i++) {
    //            List<TreeNode> leftSide = generate(start, i - 1);
    //            List<TreeNode> rightSide = generate(i + 1, end);
    //
    //            for (TreeNode lEle : leftSide) {
    //                for (TreeNode rEle : rightSide) {
    //                    TreeNode treeNode = new TreeNode(i, lEle, rEle);
    //                    res.add(treeNode);
    //                }
    //            }
    //        }
    //
    //        return res;
    //    }

    private LinkedList<TreeNode> generateTrees(int start, int end) {
        // 存放最终结果或每次的递归结果
        LinkedList<TreeNode> res = new LinkedList<>();

        // 递归返回条件
        if (start > end) {
            res.add(null);
            return res;
        }

        // 遍历，让每个数字都做一次根节点
        for (int i = start; i <= end; i++) {
            // 遍历计算所有左子树情况集
            LinkedList<TreeNode> leftSide = generateTrees(start, i - 1);
            // 遍历计算所有右子树情况集
            LinkedList<TreeNode> rightSide = generateTrees(i + 1, end);

            // 生成结果集或递归返回结果集
            for (int j = 0; j < leftSide.size(); j++) {
                for (int k = 0; k < rightSide.size(); k++) {
                    TreeNode treeNode = new TreeNode(i, leftSide.get(j), rightSide.get(k));
                    res.add(treeNode);
                }
            }
        }

        return res;
    }
}
