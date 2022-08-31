package algorithms;

import java.util.Arrays;

/**
 * 加一
 *
 * @author samin
 * @date 2021-07-01
 */
public class PlusOne {

    public static void main(String[] args) {
        // 1,2,4
        System.out.println(Arrays.toString(new PlusOne().plusOne(new int[]{1, 2, 3})));
        // 4,3,2,3
        System.out.println(Arrays.toString(new PlusOne().plusOne(new int[]{4, 3, 2, 2})));
        // 1
        System.out.println(Arrays.toString(new PlusOne().plusOne(new int[]{0})));
        // 2,0,0
        System.out.println(Arrays.toString(new PlusOne().plusOne(new int[]{1, 9, 9})));
        // 1,0,0
        System.out.println(Arrays.toString(new PlusOne().plusOne(new int[]{9, 9})));
        // 1,8,0
        System.out.println(Arrays.toString(new PlusOne().plusOne(new int[]{1, 7, 9})));
        // 1,0
        System.out.println(Arrays.toString(new PlusOne().plusOne(new int[]{9})));
    }

    public int[] plusOne(int[] digits) {
        boolean l = false;

        for (int i = digits.length - 1; i >= 0; i--) {
            if (i == digits.length - 1 && digits.length != 1) {
                digits[i] = digits[i] + 1;

                if (digits[i] == 10) {
                    digits[i] = 0;
                    l = true;
                }

                continue;
            }

            if (i == 0) {
                if (digits.length == 1) {
                    digits[i] = digits[i] + 1;
                }

                if (l) {
                    digits[i] = digits[i] + 1;
                }

                if (digits[i] == 10) {
                    digits[i] = 0;
                    int[] newDigits = new int[digits.length + 1];
                    System.arraycopy(digits, 0, newDigits, 1, digits.length);
                    newDigits[0] = 1;
                    return newDigits;
                }

                continue;
            }

            if (l) {
                digits[i] = digits[i] + 1;
                l = false;
            }
            if (digits[i] == 10) {
                digits[i] = 0;
                l = true;
            }
        }

        return digits;
    }
}
