package com.samin.algorithm.leetcode;

/**
 * 反转字符串
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题
 */
public class ReverseString {

    public void reverseString(char[] s) {
        // 双指针做法
        for (int i = 0; i < s.length / 2; i++) {
            // 通过差值进行置换
            int diff = s[i] - s[s.length - 1 - i];
            s[i] = (char) (s[i] - diff);
            s[s.length - 1 - i] = (char) (s[s.length - 1 - i] + diff);
        }
    }

    public static void main(String[] args) {
//        char[] strs = "hello".toCharArray();
        char[] strs = "Hannah".toCharArray();
        new ReverseString().reverseString(strs);
        System.out.println(strs);
    }
}