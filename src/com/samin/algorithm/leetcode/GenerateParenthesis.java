package com.samin.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/** 有效括号生成 */
public class GenerateParenthesis {

    /** 没有考虑合法性的情况，穷举所有 */
    public List<String> generateParenthesis2(int n) {
        List<String> res = new ArrayList<>();

        generate2(0, 2 * n, "");

        return res;
    }

    public void generate2(int n, int max, String s) {
        if (n >= max) {
            System.out.println(s);
            return;
        }

        generate2(n + 1, max, s + "(");
        generate2(n + 1, max, s + ")");
    }

    /** 真正解题过程 左括号条件 <= n 右括号条件 <= 左括号 */
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();

        generate(0, 0, n, "", res);

        return res;
    }

    public void generate(int left, int right, int max, String s, List<String> res) {
        if (left == max && right == left) {
            res.add(s);
            return;
        }

        if (left < max) {
            generate(left + 1, right, max, s + "(", res);
        }

        if (right < left) {
            generate(left, right + 1, max, s + ")", res);
        }
    }

    public static void main(String[] args) {
        System.out.println(new GenerateParenthesis().generateParenthesis(3));
    }
}
