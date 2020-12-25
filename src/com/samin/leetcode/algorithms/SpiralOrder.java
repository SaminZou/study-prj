package com.samin.leetcode.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpiralOrder {

    public static void main(String[] args) {
        //        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        int[][] matrix = {};
        System.out.println(Arrays.toString(new SpiralOrder().spiralOrder(matrix)));
    }

    public int[] spiralOrder(int[][] matrix) {
        List<Integer> resultList = new ArrayList<>();
        if (matrix.length == 0) {
            return resultList.stream().mapToInt(Integer::intValue).toArray();
        }

        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int bottom = matrix.length - 1;

        while (true) {
            // left to right.
            for (int i = left; i <= right; i++) {
                resultList.add(matrix[top][i]);
            }
            top += 1;
            if (top > bottom) {
                break;
            }
            // top to bottom.
            for (int i = top; i <= bottom; i++) {
                resultList.add(matrix[i][right]);
            }
            right -= 1;
            if (left > right) {
                break;
            }
            // right to left.
            for (int i = right; i >= left; i--) {
                resultList.add(matrix[bottom][i]);
            }
            bottom -= 1;
            if (top > bottom) {
                break;
            }
            // bottom to top.
            for (int i = bottom; i >= top; i--) {
                resultList.add(matrix[i][left]);
            }
            left += 1;
            if (left > right) {
                break;
            }
        }

        return resultList.stream().mapToInt(Integer::intValue).toArray();
    }
}
