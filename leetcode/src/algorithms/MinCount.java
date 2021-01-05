package algorithms;

/** 拿硬币 */
public class MinCount {

    public static void main(String[] args) {
        System.out.println(new MinCount().minCount(new int[] {4, 2, 1}));
        System.out.println(new MinCount().minCount(new int[] {2, 3, 10}));
    }

    public int minCount(int[] coins) {
        int res = 0;

        for (int ele : coins) {
            while (ele > 0) {
                if (ele >= 2) {
                    ele = ele - 2;
                } else {
                    ele = ele - 1;
                }

                res = res + 1;
            }
        }

        return res;
    }
}
