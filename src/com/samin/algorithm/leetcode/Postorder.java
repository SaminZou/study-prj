package com.samin.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

// N叉树的后序遍历
public class Postorder {

    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList<>();

        postorder(root, res);

        return res;
    }

    public void postorder(Node root, List<Integer> res) {
        if (root != null) {
            for (Node ele : root.children) {
                postorder(ele, res);
            }
            res.add(root.val);
        }
    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}