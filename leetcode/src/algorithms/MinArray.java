package algorithms;

/**
 * 旋转数组的最小数字
 *
 * @author samin
 * @date 2021-01-11
 */
public class MinArray {

    public static void main(String[] args) {
        System.out.println(new MinArray().minArray(new int[]{3, 4, 5, 1, 2}));
        System.out.println(new MinArray().minArray(new int[]{2, 2, 2, 0, 1}));
    }

    public int minArray(int[] numbers) {
        if (numbers.length == 0) {
            return 0;
        }

        int index = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (index > numbers[i]) {
                return numbers[i];
            }
        }

        return index;
    }
}
