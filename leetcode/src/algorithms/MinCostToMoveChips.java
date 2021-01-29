package algorithms;

/**
 * 玩筹码
 *
 * @author samin
 * @date 2021-01-11
 */
public class MinCostToMoveChips {

    public int minCostToMoveChips(int[] position) {
        if (position.length == 0) {
            return 0;
        }

        int oddNums = 0;
        int evenNums = 0;

        for (int ele : position) {
            if (ele % 2 == 0) {
                oddNums += 1;
            } else {
                evenNums += 1;
            }
        }

        if (oddNums > evenNums) {
            return evenNums;
        } else {
            return oddNums;
        }
    }
}
