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

    /**
     * 广度优先搜索
     *
     * @param root 根节点
     * @return 深度
     */
    public int maxDepthByDFS(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(maxDepthByDFS(root.left), maxDepthByDFS(root.right)) + 1;
    }

    /**
     * 深度优先搜索
     *
     * @param root 根节点
     * @return 深度
     */
    public int maxDepthByBFS(TreeNode root) {
        int result = 0;

        if (root == null) {
            return result;
        }

        Queue<TreeNode> rootQueue = new LinkedList<>();
        rootQueue.add(root);
        while (rootQueue.size() > 0) {
            ++result;
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
