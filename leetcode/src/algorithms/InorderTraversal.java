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
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        t1.right = t2;
        t2.left = t3;

        System.out.println(new InorderTraversal().inorderTraversal(t1));
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
