package com.samin.leetcode.algorithms;

public class ToLowerCase {

    public static void main(String[] args) {
        System.out.println(new ToLowerCase().toLowerCase("Hello"));
        System.out.println(new ToLowerCase().toLowerCase("here"));
        System.out.println(new ToLowerCase().toLowerCase("LOVELY"));
        System.out.println(new ToLowerCase().toLowerCase("al&phaBET"));
    }

    public String toLowerCase(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= 65 && chars[i] <= 90) {
                chars[i] = (char) (chars[i] + 32);
            }
        }
        return new String(chars);
    }
}
