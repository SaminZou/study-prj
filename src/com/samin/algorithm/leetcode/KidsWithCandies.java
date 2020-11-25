package com.samin.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class KidsWithCandies {

    public static void main(String[] args) {
        List<Boolean> result1 = new KidsWithCandies().kidsWithCandies(new int[] {2, 3, 5, 1, 3}, 3);
        List<Boolean> result2 = new KidsWithCandies().kidsWithCandies(new int[] {4, 2, 1, 1, 2}, 1);
        List<Boolean> result3 = new KidsWithCandies().kidsWithCandies(new int[] {12, 1, 12}, 10);
        System.out.println();
    }

    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        if (candies.length == 0) {
            return null;
        }

        int max = 0;
        // 查找最大数
        for (int ele : candies) {
            if (ele > max) {
                max = ele;
            }
        }

        // 生成结果
        List<Boolean> resultList = new ArrayList<>();
        for (int i = 0; i < candies.length; i++) {
            resultList.add(candies[i] + extraCandies >= max);
        }

        return resultList;
    }
}
