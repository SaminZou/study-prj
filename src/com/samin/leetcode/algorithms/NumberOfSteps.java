package com.samin.leetcode.algorithms;

public class NumberOfSteps {

    public static void main(String[] args) {
        System.out.println(new NumberOfSteps().numberOfSteps(14));
        System.out.println(new NumberOfSteps().numberOfSteps(8));
        System.out.println(new NumberOfSteps().numberOfSteps(123));
    }

    public int numberOfSteps(int num) {
        int result = 0;
        while (num != 0) {
            if (num % 2 == 0) {
                num = num / 2;
            } else {
                num = num - 1;
            }
            result = result + 1;
        }
        return result;
    }
}
