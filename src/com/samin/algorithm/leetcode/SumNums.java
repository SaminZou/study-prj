package com.samin.algorithm.leetcode;

public class SumNums {

    int res = 0;

    // "&&" 与和 "||" 或 有短路效应
    // if(A&&B) // 若 A 为 false，则B判断不会执行（即短路），直接判定A&＆Ｂ为false
    // if(A||B) // 若 A 为 true，则B的判断不会执行（即短路），直接判断A||B为true
    public int sumNums(int n) {
        boolean temp = n > 0 && sumNums(n - 1) > 0;
        res += n;
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new SumNums().sumNums(9));
    }
}
