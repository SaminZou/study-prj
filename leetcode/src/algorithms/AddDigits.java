package algorithms;

/**
 * 各位相加
 *
 * @author samin
 * @date Truncated class file
 */
public class AddDigits {

    public static void main(String[] args) {
        // 0
        System.out.println(new AddDigits().addDigits(0));

        // 8
        System.out.println(new AddDigits().addDigits(8));

        // 11
        System.out.println(new AddDigits().addDigits(38));

        // 3
        System.out.println(new AddDigits().addDigits(111));

        // 9
        System.out.println(new AddDigits().addDigits(999));

        // 10
        System.out.println(new AddDigits().addDigits(19));
    }

    public int addDigits(int num) {
        if (num < 10) {
            return num;
        }

        var sum = 0;

        while (num > 0) {
            sum += num % 10;
            num = num / 10;

            if (num == 0 && sum >= 10) {
                num = sum;
                sum = 0;
            }
        }

        return sum;
    }
}
