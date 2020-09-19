package com.samin.leetcode;

public class Add {
    public int add(int a, int b) {
        while (b != 0) { // 直到无进位结束算法
            int tmp = a ^ b; // 无进位的加法运算
            b = (a & b) << 1; // 计算出进位左移一位做下一轮加法
            a = tmp;
        }

        return a;
    }

    public static void main(String[] args) {
        System.out.println(new Add().add(2147483647, 2147483647));
        System.out.println(new Add().add(2, -10));
        System.out.println(new Add().add(888, 22));
    }
}
