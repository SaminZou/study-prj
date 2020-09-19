package com.samin.leetcode;

import java.util.Arrays;

public class DailyTemperatures {
    public int[] dailyTemperatures(int[] T) {
        int[] result = new int[T.length];
        for (int i = 0; i < T.length; i++) {
            if (i == T.length - 1) {
                result[i] = 0;
            } else {
                for (int j = i; j < T.length; j++) {
                    if (T[j] > T[i]) {
                        result[i] = j - i;
                        break;
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] result = new DailyTemperatures().dailyTemperatures(
                new int[]{73, 74, 75, 71, 69, 72, 76, 73});

        System.out.println(Arrays.toString(result));
    }
}
