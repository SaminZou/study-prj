package com.samin.algorithm.leetcode;

public class AddStrings {

    public String addStrings(String num1, String num2) {
        // 双指针、进位、结果存储
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();

        // 转换为char数组
        char[] num1Chars = num1.toCharArray();
        char[] num2Chars = num2.toCharArray();

        // 遍历相加
        while (i >= 0 || j >= 0) {
            int tmp = 0;

            // '0' 的ASCII码对应为 48 , 所以 0~9 字符转换为整数应该 -48
            if (i >= 0) {
                //                tmp = tmp + num1Chars[i] - '0';
                tmp = tmp + num1Chars[i] - 48;
            }
            if (j >= 0) {
                tmp = tmp + num2Chars[j] - 48;
            }

            // 是否有进位
            if (carry > 0) {
                tmp = tmp + 1;
            }

            // 判断相加后的结果是否有进位
            carry = 0;
            if (tmp / 10 > 0) {
                carry = 1;
            }

            sb.append(tmp % 10);

            i = i - 1;
            j = j - 1;
        }

        // 位数相同的情况，最后需要再次检验一次是否进位
        if (carry > 0) {
            sb.append('1');
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(new AddStrings().addStrings("11", "0"));
        System.out.println(new AddStrings().addStrings("0", "0"));
        System.out.println(new AddStrings().addStrings("999", "111"));
    }
}
