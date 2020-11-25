package com.samin.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// 全排列
public class Permute {

    List<List<Integer>> res = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(new Permute().permute(new int[] {1, 2, 3}));
    }

    public List<List<Integer>> permute(int[] nums) {
        perm(nums, 0, nums.length - 1);

        return res;
    }

    public void perm(int[] nums, int start, int end) {
        if (start == end) {
            res.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
        } else {
            for (int i = start; i <= end; i++) {
                swap(nums, start, i);
                perm(nums, start + 1, end);
                swap(nums, start, i);
            }
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
