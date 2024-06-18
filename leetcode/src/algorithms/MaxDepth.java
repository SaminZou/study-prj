package algorithms;

import algorithms.base.model.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的最大深度 / 二叉树的深度
 *
 * @author samin
 * @date 2021-01-11
 */
public class MaxDepth {

    public static void main(String[] args) {
        TreeNode t5 = new TreeNode(7);
        TreeNode t4 = new TreeNode(5);
        TreeNode t3 = new TreeNode(20);
        TreeNode t2 = new TreeNode(9, t4, t5);
        TreeNode t1 = new TreeNode(3, t2, t3);

        System.out.println(maxBreadthByBFS(t1));
    }

    /**
     * 广度优先搜索(DFS)
     *
     * @param root 根节点
     * @return 深度
     */
    public static int maxDepthByDFS(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(maxDepthByDFS(root.left), maxDepthByDFS(root.right)) + 1;
    }

    /**
     * 深度优先搜索(BFS)
     *
     * @param root 根节点
     * @return 深度
     */
    public static int maxBreadthByBFS(TreeNode root) {
        int result = 0;

        if (root == null) {
            return result;
        }

        Queue<TreeNode> rootQueue = new LinkedList<>();
        rootQueue.add(root);
        while (!rootQueue.isEmpty()) {
            ++result;
            // 每一层在当前循环处理完
            for (int i = rootQueue.size(); i > 0; i--) {
                TreeNode node = rootQueue.poll();
                if (node.left != null) {
                    rootQueue.add(node.left);
                }
                if (node.right != null) {
                    rootQueue.add(node.right);
                }
            }
        }

        return result;
    }
}
