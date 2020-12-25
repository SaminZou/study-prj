package com.samin.leetcode.algorithms;

import java.util.Stack;

public class BackspaceCompare {

    public static void main(String[] args) {
        // true
        System.out.println(new BackspaceCompare().backspaceCompare("ab#c", "ad#c"));

        // true
        System.out.println(new BackspaceCompare().backspaceCompare("ab##", "c#d#"));

        // true
        System.out.println(new BackspaceCompare().backspaceCompare("a##c", "#a#c"));

        // false
        System.out.println(new BackspaceCompare().backspaceCompare("a#c", "b"));

        // true
        System.out.println(new BackspaceCompare().backspaceCompare("y#fo##f", "y#f#o##f"));

        // false
        System.out.println(new BackspaceCompare().backspaceCompare("abcd", "bbcd"));
    }

    public boolean backspaceCompare(String S, String T) {
        // 声明两个栈
        Stack<Character> sStack = new Stack<>();
        Stack<Character> tStack = new Stack<>();

        // 遍历 S 字符串，生成新字符
        for (Character ele : S.toCharArray()) {
            if (ele == '#' && sStack.size() > 0) {
                sStack.pop();
            }

            if (ele != '#') {
                sStack.push(ele);
            }
        }

        // 遍历 T 字符串，生成新字符
        for (Character ele : T.toCharArray()) {
            if (ele == '#' && tStack.size() > 0) {
                tStack.pop();
            }

            if (ele != '#') {
                tStack.push(ele);
            }
        }

        // 新字符长度需要相等
        if (sStack.size() != tStack.size()) {
            return false;
        }

        // 判断字符的具体内容
        int length = sStack.size();
        for (int i = 0; i < length; i++) {
            if (!sStack.pop().equals(tStack.pop())) {
                return false;
            }
        }

        return true;
    }
}
