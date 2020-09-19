package com.samin.coding.Q7;

import java.math.BigDecimal;
import java.util.Arrays;

public class BigDecimalUseCase {
    public static void main(String[] args) {
        BigDecimal a = new BigDecimal(3);
        BigDecimal b = new BigDecimal(2);

        // 比较 compareTo ： -1 小于； 0 等于； 1 大于
        System.out.println(a.compareTo(b)); // 1
        System.out.println(a.compareTo(a)); // 0
        System.out.println(b.compareTo(a)); // -1

        // 加法 add
        System.out.println(a.add(b).toString()); // 5

        // 减法 subtract
        System.out.println(a.subtract(b)); // 1

        // 乘法 multiply
        System.out.println(a.multiply(b).toString()); // 6

        // 除法 divide
        System.out.println(a.divide(b).toString()); // 1

        // 处理小数 setScale()
        BigDecimal c = new BigDecimal("1.234");
        System.out.println(c.setScale(2, BigDecimal.ROUND_HALF_UP)); // 1.23 四舍五入

        // 求余 divideAndRemainder
        System.out.println(Arrays.toString(a.divideAndRemainder(b))); // [1 , 1] 第一位为商，第二位为余数
    }
}
