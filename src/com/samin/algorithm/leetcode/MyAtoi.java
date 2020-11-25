package com.samin.algorithm.leetcode;

public class MyAtoi {

    public static void main(String[] args) {
        System.out.println(new MyAtoi().myAtoi("42"));
        System.out.println(new MyAtoi().myAtoi("-42"));
        System.out.println(new MyAtoi().myAtoi("4193 with words"));
        System.out.println(new MyAtoi().myAtoi("words and 987"));
        System.out.println(new MyAtoi().myAtoi("-91283472332"));
        System.out.println(new MyAtoi().myAtoi("91283472332"));
        System.out.println(new MyAtoi().myAtoi(" "));
        System.out.println(new MyAtoi().myAtoi("+1"));
        System.out.println(new MyAtoi().myAtoi("+"));
        System.out.println(new MyAtoi().myAtoi("-"));
        System.out.println(new MyAtoi().myAtoi("20000000000000000000"));
        System.out.println(new MyAtoi().myAtoi("2147483648"));
        System.out.println(new MyAtoi().myAtoi("2147483646"));
        System.out.println(new MyAtoi().myAtoi("  0000000000012345678"));
        System.out.println(new MyAtoi().myAtoi("+-2"));
        System.out.println(new MyAtoi().myAtoi("2147483800"));
    }

    public int myAtoi(String str) {
        int result = 0;

        // 去除收尾空格
        str = str.trim();
        // 是否无效输入
        if (str.length() != 0) {
            // 判断正负
            boolean isNegNum = false;
            if (str.charAt(0) == 45 || str.charAt(0) == 43) {
                if (str.charAt(0) == 45) {
                    isNegNum = true;
                }
                str = str.substring(1);
            }

            // 去除开头的0
            while (str.length() > 0 && str.charAt(0) == 48) {
                str = str.substring(1);
            }

            // 取有效部分
            StringBuilder sb = new StringBuilder();
            for (char ele : str.toCharArray()) {
                if (ele >= 48 && ele <= 57) {
                    sb.append(ele);
                } else {
                    break;
                }
            }
            str = sb.toString();
            if (str.length() == 0) {
                return result;
            }

            // 计算结果
            char[] numsArr = str.toCharArray();
            if (isNegNum) {
                if (numsArr.length > 10) {
                    return Integer.MIN_VALUE;
                }
            } else {
                if (numsArr.length > 10) {
                    return Integer.MAX_VALUE;
                }
            }
            for (int i = 0; i < numsArr.length; i++) {
                // 边界判断
                if (result > Integer.MAX_VALUE / 10) {
                    if (isNegNum) {
                        return Integer.MIN_VALUE;
                    } else {
                        return Integer.MAX_VALUE;
                    }
                }
                if (result == Integer.MAX_VALUE / 10) {
                    if (isNegNum && Character.digit(numsArr[i], 10) > 8) {
                        return Integer.MIN_VALUE;
                    }
                    if (!isNegNum && Character.digit(numsArr[i], 10) > 7) {
                        return Integer.MAX_VALUE;
                    }
                }
                result = result * 10 + Character.digit(numsArr[i], 10);
            }

            result = isNegNum ? -result : result;
        }

        return result;
    }
}
