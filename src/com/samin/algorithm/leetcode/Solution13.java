package com.samin.algorithm.leetcode;

public class Solution13 {

    public int[] printNumbers(int n) {
        if (n == 0) { // 特殊情况
            return new int[] {};
        }

        int max = (int) Math.pow(10, n) - 1; // 计算最大数
        int[] result = new int[max];
        for (int i = 0; i < max; i++) { // 遍历加载数据
            result[i] = i + 1;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] result = new Solution13().printNumbers(2);
        System.out.println(result.length);
    }
}
