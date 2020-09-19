package com.samin.algorithm.leetcode;

public class IntegerBreak {

    public int integerBreak(int n) {
        // 小于3不适用公式
        if (n <= 3) {
            return n - 1;
        }

        // 计算商和余数
        int a = n / 3;
        int b = n % 3;

        if (b == 0) {
            return (int) Math.pow(3, a);
        }

        if (b == 1) {
            return (int) Math.pow(3, a - 1) * 4;
        }

        // b == 2 , 根据余数，一共三种情况返回结果
        return (int) Math.pow(3, a) * 2;
    }
}
