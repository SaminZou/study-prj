package com.samin.leetcode.algorithms;

// 重新排列字符串
public class RestoreString {

    public static void main(String[] args) {
        // leetcode
        System.out.println(
                new RestoreString().restoreString("codeleet", new int[] {4, 5, 6, 7, 0, 2, 1, 3}));
        // abc
        System.out.println(new RestoreString().restoreString("abc", new int[] {0, 1, 2}));
        // nihao
        System.out.println(new RestoreString().restoreString("aiohn", new int[] {3, 1, 4, 2, 0}));
        // arigatou
        System.out.println(
                new RestoreString().restoreString("aaiougrt", new int[] {4, 0, 2, 6, 7, 3, 1, 5}));
        // rat
        System.out.println(new RestoreString().restoreString("art", new int[] {1, 0, 2}));
    }

    public String restoreString(String s, int[] indices) {
        // 结果
        char[] res = new char[s.length()];

        // 重新排序
        int index = 0;
        for (int ele : indices) {
            res[ele] = s.toCharArray()[index++];
        }

        return new String(res);
    }
}
