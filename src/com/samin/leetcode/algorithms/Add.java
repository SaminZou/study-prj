package com.samin.leetcode.algorithms;

/**
 * 不用加减乘除做加法
 *
 * @author samin
 * @date 2021-01-3
 */
public class Add {

    public static void main(String[] args) {
        System.out.println(new Add().add(2147483647, 2147483647));
        System.out.println(new Add().add(2, -10));
        System.out.println(new Add().add(888, 22));
    }

    public int add(int a, int b) {
        // 直到无进位结束算法
        while (b != 0) {
            // 无进位的加法运算
            int tmp = a ^ b;
            // 计算出进位左移一位做下一轮加法
            b = (a & b) << 1;
            a = tmp;
        }

        return a;
    }
}
