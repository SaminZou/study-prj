package algorithms;

import algorithms.base.model.TreeNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 二叉树的中序遍历
 *
 * @author samin
 * @date 2021-06-25
 */
public class InorderTraversal {

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

        // [0, 2, 3, 4, 5, 6, 7, 8, 9]
        System.out.println(new InorderTraversal().inorderTraversal(t6));
    }

    List<Integer> resultList = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        if (Objects.isNull(root)) {
            return resultList;
        }

        if (Objects.nonNull(root.left)) {
            inorderTraversal(root.left);
        }
        resultList.add(root.val);
        if (Objects.nonNull(root.right)) {
            inorderTraversal(root.right);
        }

        return resultList;
    }
}
