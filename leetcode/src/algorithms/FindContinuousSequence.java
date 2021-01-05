package algorithms;

import java.util.ArrayList;
import java.util.List;

/** 和为s的连续正数序列（至少含有两个数） */
public class FindContinuousSequence {

    public static void main(String[] args) {
        int[][] result1 = new FindContinuousSequence().findContinuousSequence(1);
        int[][] result2 = new FindContinuousSequence().findContinuousSequence(15);
        System.out.println();
    }

    public int[][] findContinuousSequence(int target) {
        List<int[]> resultList = new ArrayList<>();
        int mid = target / 2 + 1;
        for (int i = 1; i < mid; i++) {
            int sum = 0;
            sum += i;
            List<Integer> tempList = new ArrayList<>();
            tempList.add(i);
            for (int j = i + 1; sum < target; j++) {
                sum += j;
                tempList.add(j);
                if (sum == target) {
                    int[] temp = new int[tempList.size()];
                    for (int k = 0; k < tempList.size(); k++) {
                        temp[k] = tempList.get(k);
                    }
                    resultList.add(temp);
                }
            }
        }

        return resultList.toArray(new int[0][]);
    }
}
