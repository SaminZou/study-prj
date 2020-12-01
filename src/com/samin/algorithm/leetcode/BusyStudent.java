package com.samin.algorithm.leetcode;

// 在既定时间做作业的学生人数
public class BusyStudent {

    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int res = 0;

        for (int i = 0; i < startTime.length; i++) {
            if (startTime[i] <= queryTime && queryTime <= endTime[i]) {
                res = res + 1;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        // 1
        System.out.println(
                new BusyStudent().busyStudent(new int[] {1, 2, 3}, new int[] {3, 2, 7}, 4));
        // 1
        System.out.println(new BusyStudent().busyStudent(new int[] {4}, new int[] {4}, 4));
        // 0
        System.out.println(new BusyStudent().busyStudent(new int[] {4}, new int[] {4}, 5));
        // 0
        System.out.println(
                new BusyStudent().busyStudent(new int[] {1, 1, 1, 1}, new int[] {1, 3, 2, 4}, 7));
        // 5
        System.out.println(
                new BusyStudent()
                        .busyStudent(
                                new int[] {9, 8, 7, 6, 5, 4, 3, 2, 1},
                                new int[] {10, 10, 10, 10, 10, 10, 10, 10, 10},
                                5));
    }
}
