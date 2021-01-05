package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DecompressRLElist {

    public static void main(String[] args) {
        System.out.println(
                Arrays.toString(new DecompressRLElist().decompressRLElist(new int[] {1, 2, 3, 4})));
        System.out.println(
                Arrays.toString(new DecompressRLElist().decompressRLElist(new int[] {1, 1, 2, 3})));
    }

    public int[] decompressRLElist(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i = i + 2) {
            for (int j = 0; j < nums[i]; j++) {
                res.add(nums[i + 1]);
            }
        }
        return res.stream().mapToInt(Integer::valueOf).toArray();
    }
}
