package com.samin.leetcode.algorithms;

public class Add {

    public static void main(String[] args) {
        System.out.println(new Add().add(2147483647, 2147483647));
        System.out.println(new Add().add(2, -10));
        System.out.println(new Add().add(888, 22));
    }

    public int add(int a, int b) {
        // 直到无进位结束算法
        while (b != 0) {
            int tmp = a ^ b; // 无进位的加法运算
            b = (a & b) << 1; // 计算出进位左移一位做下一轮加法
            a = tmp;
        }

        return a;
    }
}
