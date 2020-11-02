package com.samin.algorithm.leetcode;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Intersection {

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        for (Integer ele : nums1) {
            set1.add(ele);
        }

        Set<Integer> set2 = new HashSet<>();
        for (Integer ele : nums2) {
            set2.add(ele);
        }

        set1 =
                set1.stream()
                        .sorted(Comparator.naturalOrder())
                        .collect(Collectors.toCollection(LinkedHashSet::new));
        set2 =
                set2.stream()
                        .sorted(Comparator.naturalOrder())
                        .collect(Collectors.toCollection(LinkedHashSet::new));

        int[] res = new int[Math.max(set1.size(), set2.size())];

        return null;
    }

    public static void main(String[] args) {}
}
