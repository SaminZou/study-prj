package algorithms;

import java.util.*;

public class SortByBits {

    public static void main(String[] args) {
        // [0,1,2,4,8,3,5,6,7]
        System.out.println(
                Arrays.toString(
                        new SortByBits().sortByBits(new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8})));
        // [1,2,4,8,16,32,64,128,256,512,1024]
        System.out.println(
                Arrays.toString(
                        new SortByBits()
                                .sortByBits(
                                        new int[] {1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1})));
        // [10000,10000]
        System.out.println(Arrays.toString(new SortByBits().sortByBits(new int[] {10000, 10000})));
        // [2,3,5,17,7,11,13,19]
        System.out.println(
                Arrays.toString(
                        new SortByBits().sortByBits(new int[] {2, 3, 5, 7, 11, 13, 17, 19})));
        // [10,100,10000,1000]
        System.out.println(
                Arrays.toString(new SortByBits().sortByBits(new int[] {10, 100, 1000, 10000})));
    }

    public int[] sortByBits(int[] arr) {
        // 计算 1 的数量，分门别类，使用 TreeMap 的特性（ sort by key ）
        Map<Integer, List<Integer>> filterPartRes = new TreeMap<>();
        for (int ele : arr) {
            int bits = bits(ele);

            if (filterPartRes.containsKey(bits)) {
                List<Integer> temps = filterPartRes.get(bits);
                temps.add(ele);
                filterPartRes.put(bits, temps);
            } else {
                List<Integer> vals = new ArrayList<>();
                vals.add(ele);
                filterPartRes.put(bits, vals);
            }
        }

        // 组合结果
        int currentArrIndex = 0;
        int[] res = new int[arr.length];
        for (Integer key : filterPartRes.keySet()) {
            List<Integer> tempList = filterPartRes.get(key);
            // 排列结果
            tempList.sort(Comparator.naturalOrder());

            for (Integer ele : tempList) {
                res[currentArrIndex] = ele;
                currentArrIndex += 1;
            }
        }

        return res;
    }

    // 计算 1 的数量
    private int bits(int i) {
        int res = 0;
        while (i != 0) {
            res += i & 1;
            i = i >> 1;
        }
        return res;
    }
}
