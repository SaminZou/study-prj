package com.samin.leetcode.algorithms;

// 速算机器人
public class FastCalculateRobot {

    public int calculate(String s) {
        int x = 1, y = 0;

        for (Character e : s.toCharArray()) {
            if (e.equals('A')) {
                x = 2 * x + y;
            }

            if (e.equals('B')) {
                y = 2 * y + x;
            }
        }

        return x + y;
    }
}
