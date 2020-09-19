package com.samin.leetcode;

public class Solution8 {
    public String reverseLeftWords(String s, int n) {
        if (s.length() == 0 || n == 0) { // 特殊情况
            return s;
        }

        char[] chars = new char[n]; // 字符数组暂存
        for (int i = 0; i < n; i++) {
            chars[i] = s.charAt(i); // 加入规定长度的字符
        }

        return s.substring(n) + new String(chars); // 返回拼接结果
    }

    public static void main(String[] args) {
        System.out.println(new Solution8().reverseLeftWords("", 0));
    }
}
