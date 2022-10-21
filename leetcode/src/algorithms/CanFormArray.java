package algorithms;

import java.util.HashMap;
import java.util.Map;

/**
 * 能否连接形成数组
 *
 * @author samin
 * @date 2021-01-11
 */
public class CanFormArray {

    // 时间太长

    //    StringBuilder des;
    //    boolean res = false;
    //
    //    public boolean canFormArray(int[] arr, int[][] pieces) {
    //        des = new StringBuilder(arr.length);
    //
    //        // 拼接 arr 字符串做为判断条件
    //        for (int ele : arr) {
    //            des.append(ele);
    //        }
    //
    //        // 使用递归拼接 pieces 是否用满足条件的组合
    //        perm(pieces, 0, pieces.length - 1);
    //
    //        return res;
    //    }
    //
    //    // 全排列方法
    //    public void perm(int[][] pieces, int start, int end) {
    //        if (start == end) {
    //            StringBuilder temp = new StringBuilder();
    //            for (int[] ele : pieces) {
    //                for (int inner : ele) {
    //                    temp.append(inner);
    //                }
    //            }
    //
    //            if (des.toString().equals(temp.toString())) {
    //                res = true;
    //            }
    //        } else {
    //            for (int i = start; i <= end; i++) {
    //                swap(pieces, start, i);
    //                perm(pieces, start + 1, end);
    //                swap(pieces, start, i);
    //            }
    //        }
    //    }
    //
    //    public void swap(int[][] pieces, int i, int j) {
    //        int[] temp = pieces[i];
    //        pieces[i] = pieces[j];
    //        pieces[j] = temp;
    //    }

    public static void main(String[] args) {
        // true
        System.out.println(new CanFormArray().canFormArray(new int[]{85}, new int[][]{new int[]{85}}));
        // true
        System.out.println(new CanFormArray().canFormArray(new int[]{15, 88}, new int[][]{new int[]{88}, new int[]{15}}));
        // false
        System.out.println(new CanFormArray().canFormArray(new int[]{49, 18, 16}, new int[][]{new int[]{16, 18, 49}}));
        // true
        System.out.println(new CanFormArray().canFormArray(new int[]{91, 4, 64, 78},
                new int[][]{new int[]{78}, new int[]{4, 64}, new int[]{91}}));
        // false
        System.out.println(new CanFormArray().canFormArray(new int[]{1, 3, 5, 7}, new int[][]{new int[]{2, 4, 6, 8}}));
    }

    public boolean canFormArray(int[] arr, int[][] pieces) {
        // 以 pieces 每个子数组的第一个元素为 key 存储到 map 里面
        Map<Integer, int[]> temp = new HashMap<>();
        for (int[] ele : pieces) {
            temp.put(ele[0], ele);
        }

        int index = 0;
        // 遍历 arr ， 遍历所有元素
        while (index < arr.length) {
            // 在 map 里面看是否能查到元素
            if (temp.containsKey(arr[index])) {
                int current = arr[index];
                // 对于 pieces 子数组长度大于 1 的数组进行遍历查询，看连续的 arr 元素是否能够匹配
                if (temp.get(arr[index]).length > 1) {
                    for (int j = 1; j < temp.get(current).length; j++) {
                        index += 1;
                        if (arr[index] != temp.get(current)[j]) {
                            return false;
                        }
                    }
                }
            } else {
                return false;
            }
            index += 1;
        }

        return true;
    }
}
