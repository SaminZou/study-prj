package com.samin.leetcode.algorithms;

import java.util.ArrayList;
import java.util.List;

/** 有效括号生成 */
public class GenerateParenthesis {

    public static void main(String[] args) {
        // 打印 3 对括号的所有有效组合
        System.out.println(new GenerateParenthesis().generateParenthesis(3));

        System.out.println("---------------------------------------");

        // 打印 3 对括号的所有组合
        System.out.println(new GenerateParenthesis().generateParenthesis2(3));
    }

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();

        generate(0, 0, n, "", res);

        return res;
    }

    public void generate(int left, int right, int max, String s, List<String> res) {
        // 左括号条件 == max 右括号条件 == 左括号
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

    public List<String> generateParenthesis2(int n) {
        List<String> res = new ArrayList<>();

        generate2(0, 2 * n, "", res);

        return res;
    }

    public void generate2(int n, int max, String s, List<String> res) {
        // 没有考虑合法性的情况，穷举所有
        if (n >= max) {
            res.add(s);
            return;
        }

        generate2(n + 1, max, s + "(", res);
        generate2(n + 1, max, s + ")", res);
    }
}
