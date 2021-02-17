package com.samin.leetcode;

public class Solution21 {
    public int fib(int N) {
        if (N == 0) {
            return 0;
        }

        if (N == 1) {
            return 1;
        }

        // 注意有F(0)的情况
        int[] arr = new int[N + 1];
        arr[0] = 0;
        arr[1] = 1;
        for (int i = 2; i <= N; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }

        return arr[N];
    }

    public static void main(String[] args) {
        System.out.println(new Solution21().fib(2));
        System.out.println(new Solution21().fib(3));
        System.out.println(new Solution21().fib(4));
    }
}
