package algorithms;

/**
 * 青蛙跳台阶问题
 *
 * @author samin
 * @date 2021-01-11
 */
public class NumWays {

    public static void main(String[] args) {
        System.out.println(new NumWays().numWays(2));
        System.out.println(new NumWays().numWays(7));
        System.out.println(new NumWays().numWays(46));
    }

    public int numWays(int n) {
        int q, p = 0;
        int r = 1;

        for (int i = 0; i < n; i++) {
            q = p;
            p = r;
            r = (p + q) % 1000000007;
        }

        return r;
    }
}
