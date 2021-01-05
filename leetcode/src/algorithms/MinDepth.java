package algorithms;

import algorithms.base.model.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class MinDepth {

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(3);
        TreeNode t2 = new TreeNode(9);
        TreeNode t3 = new TreeNode(20);
        TreeNode t4 = new TreeNode(15);
        TreeNode t5 = new TreeNode(7);

        TreeNode t6 = new TreeNode(1);
        TreeNode t7 = new TreeNode(2);
        TreeNode t8 = new TreeNode(3);
        t2.left = t6;
        t2.right = t7;
        t1.left = t2;
        t1.right = t3;
        t3.left = t4;
        t3.right = t5;
        t4.left = t8;
        System.out.println(new MinDepth().minDepth(t1));
    }

    public int minDepth(TreeNode root) {
        // 特殊情况处理
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> rootQueue = new LinkedList<>();
        rootQueue.add(root);

        int depth = 1;
        boolean isFound = false;
        while (rootQueue.size() > 0 && !isFound) {
            for (int i = rootQueue.size(); i > 0; i--) {
                TreeNode ele = rootQueue.poll();

                // 直接返回的情况
                if (ele.right == null && ele.left == null) {
                    isFound = true;
                    break;
                }

                if (ele.right != null) {
                    rootQueue.add(ele.right);
                }

                if (ele.left != null) {
                    rootQueue.add(ele.left);
                }
            }

            if (!isFound) {
                depth += 1;
            }
        }

        return depth;
    }
}
