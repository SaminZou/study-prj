package com.samin.leetcode.algorithms;

/**
 * 文件夹操作日志搜集器
 *
 * @author samin
 * @date 2020-12-24
 */
public class MinOperations {

    public int minOperations(String[] logs) {
        int res = 0;

        for (String ele : logs) {
            if (ele.startsWith("../") && res > 0) {
                res -= 1;
            }

            if (!(ele.startsWith("./") || ele.startsWith("../"))) {
                res += 1;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        // 2
        System.out.println(
                new MinOperations()
                        .minOperations(new String[] {"d1/", "d2/", "../", "d21/", "./"}));

        // 3
        System.out.println(
                new MinOperations()
                        .minOperations(new String[] {"d1/", "d2/", "./", "d3/", "../", "d31/"}));

        // 0
        System.out.println(
                new MinOperations().minOperations(new String[] {"d1/", "../", "../", "../"}));
    }
}
