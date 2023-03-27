package algorithms;

/**
 * 整数的各位积和之差
 *
 * <p> https://leetcode-cn.com/problems/subtract-the-product-and-sum-of-digits-of-an-integer/
 *
 * @author samin
 * @date 2021-01-11
 */
public class SubtractProductAndSum {

    public static void main(String[] args) {
        // 15
        System.out.println(new SubtractProductAndSum().subtractProductAndSum(234));
        // 21
        System.out.println(new SubtractProductAndSum().subtractProductAndSum(4421));
    }

    public int subtractProductAndSum(int n) {
        // sum：和；difference：差；product：积；quotient：商
        int sum = 0;
        int product = 1;

        while (n > 0) {
            sum += n % 10;
            product *= n % 10;
            n = n / 10;
        }

        return product - sum;
    }
}
