package com.samin.algorithm.leetcode;

import java.util.ArrayList;

public class CountBinarySubstrings {

    public int countBinarySubstrings(String s) {
        ArrayList<Integer> counts = new ArrayList<>();

        int ptr = 0;
        while (ptr < s.length()) {
            char index = s.charAt(ptr);
            int count = 0;
            while (ptr < s.length() && s.charAt(ptr) == index) {
                ptr = ptr + 1;
                count = count + 1;
            }
            counts.add(count);
        }

        int res = 0;
        for (int i = 1; i < counts.size(); i++) {
            res = res + Math.min(counts.get(i - 1), counts.get(i));
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new CountBinarySubstrings().countBinarySubstrings("00110011"));
        System.out.println(new CountBinarySubstrings().countBinarySubstrings("10101"));
    }
}
