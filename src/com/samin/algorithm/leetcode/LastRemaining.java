package com.samin.algorithm.leetcode;

public class LastRemaining {

    public int lastRemaining(int n, int m) {
        int ans = 0;
        for (int i = 2; i <= n; i++) {
            ans = (ans + m) % i;
        }
        return ans;
    }

    public static void main(String[] args) {
        //        System.out.println(new LastRemaining().lastRemaining(5, 3));
        //        System.out.println(new LastRemaining().lastRemaining(10, 17));
        //        System.out.println(new LastRemaining().lastRemaining(5, 1));
        System.out.println(new LastRemaining().lastRemaining(70866, 116922));
    }
}
