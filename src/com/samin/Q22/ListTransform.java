package com.samin.Q22;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListTransform {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(111);
        // List<Integer> -> int[]
        int[] ints = list.stream().mapToInt(Integer::valueOf).toArray();
        System.out.println(ints.length);

        // List<Integer> -> Integer[]
        Integer[] integers1 = list.toArray(new Integer[0]);

        // int[] -> List<Integer>
        List<Integer> collect = Arrays.stream(ints).boxed().collect(Collectors.toList());
        System.out.println(collect.size());

        // int[] -> Integer[]
        Integer[] integers = Arrays.stream(ints).boxed().toArray(Integer[]::new);

        //Integer[] -> int[]
        int[] ints1 = Arrays.stream(integers1).mapToInt(Integer::valueOf).toArray();

        //Integer[] -> List<Integer>
        List<Integer> integers2 = new ArrayList<>(Arrays.asList(integers1));
    }
}
