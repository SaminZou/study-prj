package com.samin.leetcode.algorithms;

// 有效的山脉数组
public class ValidMountainArray {

    public static void main(String[] args) {
        // false false true true
        System.out.println(new ValidMountainArray().validMountainArray(new int[] {2, 1}));
        System.out.println(new ValidMountainArray().validMountainArray(new int[] {3, 5, 5}));
        System.out.println(new ValidMountainArray().validMountainArray(new int[] {0, 3, 2, 1}));
        System.out.println(
                new ValidMountainArray().validMountainArray(new int[] {0, 1, 2, 4, 2, 1}));
        System.out.println(new ValidMountainArray().validMountainArray(new int[] {5, 4, 3, 2, 1}));
    }

    public boolean validMountainArray(int[] A) {
        // 边界判断
        if (A.length < 3) {
            return false;
        }

        // 即是结果，又是状态控制（控制只能转换一次方向）
        boolean isMountainArray = false;
        // 计数器解决连续下坡的问题
        int counter = 0;
        int index = 0;
        while (index < A.length - 1) {
            if (isMountainArray) {
                // 不能出现平坡或者逆坡
                if (A[index] == A[index + 1] || A[index] < A[index + 1]) {
                    return false;
                }
            } else {
                // 不能出现平坡
                if (A[index] == A[index + 1]) {
                    return false;
                }

                if (A[index] > A[index + 1]) {
                    isMountainArray = true;
                } else {
                    counter += 1;
                }
            }
            index += 1;
        }

        return counter > 0 && isMountainArray;
    }
}
