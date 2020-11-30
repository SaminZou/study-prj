package com.samin.algorithm.leetcode;

// 最富有客户的资产总量
public class MaximumWealth {

    public int maximumWealth(int[][] accounts) {
        int max = 0;

        int temp;
        for (int[] user : accounts) {
            temp = 0;
            for (int money : user) {
                temp += money;
            }
            max = Math.max(max, temp);
        }

        return max;
    }

    public static void main(String[] args) {
        // 6
        System.out.println(new MaximumWealth().maximumWealth(new int[][] {{1, 2, 3}, {3, 2, 1}}));
        // 10
        System.out.println(new MaximumWealth().maximumWealth(new int[][] {{1, 5}, {7, 3}, {3, 5}}));
        // 17
        System.out.println(
                new MaximumWealth().maximumWealth(new int[][] {{2, 8, 7}, {7, 1, 3}, {1, 9, 5}}));
    }
}
