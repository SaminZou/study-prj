package algorithms;

import java.util.HashMap;

public class SingleNumber {

    public static void main(String[] args) {
        System.out.println(new SingleNumber().singleNumber(new int[] {3, 4, 3, 3}));
        System.out.println(new SingleNumber().singleNumber(new int[] {9, 1, 7, 9, 7, 9, 7}));
    }

    public int singleNumber(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        HashMap<Integer, Integer> sumMap = new HashMap<>();
        for (int ele : nums) {
            if (sumMap.containsKey(ele)) {
                sumMap.put(ele, sumMap.get(ele) + 1);
            } else {
                sumMap.put(ele, 1);
            }
        }

        for (Integer ele : sumMap.keySet()) {
            if (sumMap.get(ele) == 1) {
                return ele;
            }
        }

        return 0;
    }
}
