package algorithms;

import algorithms.base.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉搜索树的最近公共祖先
 * <p>
 * Description: 二叉搜索树的最近公共祖先
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2024-06-17
 */
public class LowestCommonAncestor4BST {

    public static void main(String[] args) {
        TreeNode t9 = new TreeNode(9);
        TreeNode t7 = new TreeNode(7);
        TreeNode t0 = new TreeNode(0);
        TreeNode t5 = new TreeNode(5);
        TreeNode t3 = new TreeNode(3);
        TreeNode t8 = new TreeNode(8, t7, t9);
        TreeNode t4 = new TreeNode(4, t3, t5);
        TreeNode t2 = new TreeNode(2, t0, t4);
        TreeNode t6 = new TreeNode(6, t2, t8);

        // 6
        System.out.println(new LowestCommonAncestor4BST().lowestCommonAncestor(t6, t2, t8).val);
        // 2
        System.out.println(new LowestCommonAncestor4BST().lowestCommonAncestor(t6, t2, t4).val);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> path1 = path(root, p);
        List<TreeNode> path2 = path(root, q);

        // 用最短路径来截取
        int min = Math.min(path1.size(), path2.size());

        TreeNode lastSame = null;
        // 找出最后一个相同节点
        for (int i = 0; i < min; i++) {
            if (path1.get(i).val == path2.get(i).val) {
                lastSame = path1.get(i);
            } else {
                break;
            }
        }

        return lastSame;
    }

    private static List<TreeNode> path(TreeNode root, TreeNode des) {
        // 存放节点路径
        List<TreeNode> paths = new ArrayList<>();

        // 遍历二叉树
        while (root != null) {
            paths.add(root);
            if (root == des) {
                return paths;
            }
            if (root.val > des.val) {
                root = root.left;
            } else {
                root = root.right;
            }
        }

        return paths;
    }
}
