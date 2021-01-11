package algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * 单调数列
 *
 * @author samin
 * @date 2021-01-11
 */
public class IsMonotonic {

    public boolean isMonotonic(int[] A) {
        // 排除特别入参
        if (A.length <= 2) {
            return true;
        }

        // 双 List 记录数据
        List<Integer> increaseList = new ArrayList<>();
        List<Integer> decreaseList = new ArrayList<>();

        for (int i = 1; i < A.length; i++) {
            if (A[i] < A[i - 1]) {
                decreaseList.add(i);
            }

            if (A[i] > A[i - 1]) {
                increaseList.add(i);
            }
        }

        // 当其中一个 List 为空的情况为单调数组
        return !(!increaseList.isEmpty() && !decreaseList.isEmpty());
    }

    public static void main(String[] args) {
        // true
        System.out.println(new IsMonotonic().isMonotonic(new int[] {1, 2, 2, 3}));
        // true
        System.out.println(new IsMonotonic().isMonotonic(new int[] {6, 5, 4, 4}));
        // false
        System.out.println(new IsMonotonic().isMonotonic(new int[] {1, 3, 2}));
        // true
        System.out.println(new IsMonotonic().isMonotonic(new int[] {1, 2, 4, 5}));
        // true
        System.out.println(new IsMonotonic().isMonotonic(new int[] {1, 1, 1}));
    }
}
