package com.samin.Q7;

import java.math.BigDecimal;

public class FloatCalcError {
    public static void main(String[] args) {
        float a = 68399.22f;
        float b = 124052.96f;
        System.out.println(a+b); // 出现精度问题，结果错误
        BigDecimal bigDecimal1 = new BigDecimal(Float.toString(a));
        BigDecimal bigDecimal2 = new BigDecimal(Float.toString(b));
        BigDecimal result = bigDecimal1.add(bigDecimal2);
        System.out.println(result.toString()); // 正确
    }
}
