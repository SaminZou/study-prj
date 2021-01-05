package algorithms;

import algorithms.base.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class PostorderTraversal {

    public static void main(String[] args) {}

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        pro(root, res);

        return res;
    }

    public void pro(TreeNode root, List<Integer> res) {
        if (root != null) {
            pro(root.left, res);
            pro(root.right, res);
            res.add(root.val);
        }
    }
}
