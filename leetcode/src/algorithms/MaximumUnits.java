package algorithms;

import java.util.Collections;
import java.util.TreeMap;

/**
 * 卡车上的最大单元数
 *
 * @author samin
 * @date 2021-01-06
 */
public class MaximumUnits {

    public int maximumUnits(int[][] boxTypes, int truckSize) {
        int sum = 0;

        // 利用 TreeMap 根据 key 排序特性
        TreeMap<Integer, Integer> temp = new TreeMap<>(Collections.reverseOrder());

        // 置换单元数和个数，需要注意聚合一样单元大小的箱子
        for (int[] ele : boxTypes) {
            if (temp.containsKey(ele[1])) {
                temp.put(ele[1], temp.get(ele[1]) + ele[0]);
            } else {
                temp.put(ele[1], ele[0]);
            }
        }
        System.out.println(temp);

        // 遍历计算结果
        for (Integer unitSize : temp.keySet()) {
            if (truckSize > temp.get(unitSize)) {
                truckSize = truckSize - temp.get(unitSize);
                sum = temp.get(unitSize) * unitSize + sum;
            } else {
                sum = unitSize * truckSize + sum;
                break;
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        int[][] a = new int[][] {{5, 10}, {2, 5}, {4, 7}, {3, 9}};
        int[][] b =
                new int[][] {
                    {2, 1}, {4, 4}, {3, 1}, {4, 1}, {2, 4}, {3, 4}, {1, 3}, {4, 3}, {5, 3}, {5, 3}
                };
        System.out.println(new MaximumUnits().maximumUnits(a, 10));
        System.out.println(new MaximumUnits().maximumUnits(b, 13));
    }
}
