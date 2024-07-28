package algorithms.subject;

import algorithms.base.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树遍历
 * <p>
 * Description: 前序遍历、中序遍历、后序遍历
 * 结论：
 * - 根据前序遍历和中序遍历的结果可以确定一颗唯一二叉树
 * - BST 使用中序遍历打印出来的是升序数据
 * - 后序遍历在删除或销毁二叉树时，用于确保在删除节点前所有子节点均被删除
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2024-06-24
 */
public class BinaryTreeTraversal {

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

        // [6, 2, 0, 4, 3, 5, 8, 7, 9]
        System.out.println(preorder(t6));
        // [0, 2, 3, 4, 5, 6, 7, 8, 9]
        System.out.println(inorder(t6));
        // [0, 3, 5, 4, 2, 7, 9, 8, 6]
        System.out.println(postorder(t6));
    }

    /**
     * 前序遍历
     *
     * @param root
     * @return
     */
    private static List<Integer> preorder(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        pre(root, result);

        return result;
    }

    private static void pre(TreeNode root, List<Integer> pre) {
        if (root != null) {
            pre.add(root.val);
            pre(root.left, pre);
            pre(root.right, pre);
        }
    }

    /**
     * 中序遍历
     *
     * @param root
     * @return
     */
    private static List<Integer> inorder(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        in(root, result);

        return result;
    }

    private static void in(TreeNode root, List<Integer> in) {
        if (root != null) {
            in(root.left, in);
            in.add(root.val);
            in(root.right, in);
        }
    }

    /**
     * 后序遍历
     *
     * @param root
     * @return
     */
    private static List<Integer> postorder(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        post(root, result);

        return result;
    }

    private static void post(TreeNode root, List<Integer> post) {
        if (root != null) {
            post(root.left, post);
            post(root.right, post);
            post.add(root.val);
        }
    }
}