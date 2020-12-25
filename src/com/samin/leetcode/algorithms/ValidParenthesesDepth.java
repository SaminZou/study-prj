package com.samin.leetcode.algorithms;

import java.util.Stack;

// 有效括号的最大嵌套深度
public class ValidParenthesesDepth {

    public static void main(String[] args) {
        // 3 3 1 0
        System.out.println(new ValidParenthesesDepth().maxDepth("(1+(2*3)+((8)/4))+1"));
        System.out.println(new ValidParenthesesDepth().maxDepth("(1)+((2))+(((3)))"));
        System.out.println(new ValidParenthesesDepth().maxDepth("1+(2*3)/(2-1)"));
        System.out.println(new ValidParenthesesDepth().maxDepth("1"));
    }

    public int maxDepth(String s) {
        int res = 0;

        // 堆栈动态存储括号对
        Stack<Character> characters = new Stack<>();
        for (Character ele : s.toCharArray()) {
            // 遇到左括号，入栈并且统计最大深度
            if (ele == '(') {
                characters.push(ele);
                res = Math.max(characters.size(), res);
            }

            // 遇到右括号出栈
            if (ele == ')') {
                characters.pop();
            }
        }

        return res;
    }
}
