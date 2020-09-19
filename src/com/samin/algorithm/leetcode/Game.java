package com.samin.algorithm.leetcode;

public class Game {

    public int game(int[] guess, int[] answer) {
        int result = 0;
        for (int i = 0; i < 3; i++) {
            if (guess[i] == answer[i]) {
                result = result + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Game().game(new int[] {1, 2, 3}, new int[] {1, 2, 3}));
        System.out.println(new Game().game(new int[] {2, 2, 3}, new int[] {3, 2, 1}));
    }
}
