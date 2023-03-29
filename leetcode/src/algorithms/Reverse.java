package algorithms;

/**
 * 整数反转
 *
 * <p> https://leetcode-cn.com/problems/reverse-integer/
 *
 * @author samin
 * @date 2021-01-11
 */
public class Reverse {

    public static void main(String[] args) {
        // 321
        System.out.println(new Reverse().reverse(123));
        // -321
        System.out.println(new Reverse().reverse(-123));
        // 21
        System.out.println(new Reverse().reverse(120));
        // 0
        System.out.println(new Reverse().reverse(0));
        // -4321
        System.out.println(new Reverse().reverse(-1234));
        // 0
        System.out.println(new Reverse().reverse(1534236469));
        // 0
        System.out.println(new Reverse().reverse(-1534236469));
    }

    public int reverse(int x) {
        // 位运算优先级在加减之后
        int max = (1 << 31) - 1;
        int min = -1 << 31;
        long result = 0;

        int carry = 10;
        while (x > 0 | -x > 0) {
            result = (result * carry) + (x % 10);
            x = x / 10;
        }

        if (result > max || result < min) {
            return 0;
        }

        return (int) result;
    }
}
