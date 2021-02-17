package com.samin.algorithm.leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class Intersection {

    public int[] intersection(int[] nums1, int[] nums2) {
        // int[] to List<Integer>
        List<Integer> list1 = Arrays.stream(nums1).boxed().collect(Collectors.toList());
        List<Integer> list2 = Arrays.stream(nums2).boxed().collect(Collectors.toList());

        // do intersection
        list1.retainAll(list2);

        // delete the same number
        Set<Integer> resSet = new HashSet<>(list1);

        // return result
        return new ArrayList<>(resSet).stream().mapToInt(Integer::valueOf).toArray();
    }

    public static void main(String[] args) {
        // [2] 、 [9，4]
        System.out.println(Arrays.toString(new Intersection().intersection(new int[]{1, 2, 2, 1}, new int[]{2, 2})));
        System.out.println(Arrays.toString(new Intersection().intersection(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4})));
    }
}
