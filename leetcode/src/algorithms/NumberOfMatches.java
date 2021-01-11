package algorithms;

/**
 * 比赛中的配对次数
 *
 * @author samin
 * @date 2021-01-11
 */
public class NumberOfMatches {

    public static void main(String[] args) {
        // 6
        System.out.println(new NumberOfMatches().numberOfMatches(7));
        // 13
        System.out.println(new NumberOfMatches().numberOfMatches(14));
    }

    public int numberOfMatches(int n) {
        int res = 0;

        while (n != 1) {
            if (n % 2 == 1) {
                res += (n - 1) / 2;
                n = (n - 1) / 2 + 1;
            } else {
                res += n / 2;
                n = n / 2;
            }
        }

        return res;
    }
}
