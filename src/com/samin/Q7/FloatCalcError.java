package com.samin.Q7;

import java.math.BigDecimal;

/**
 * double和float精度不准确的问题
 * 使用java.math.BigDecimal解决
 * 数据库中的浮点数，最好用字符型来存储，方便转换成BigDecimal
 * 精度丢失
 */
public class FloatCalcError {
    public static void main(String[] args) {
        // 小数默认为double类型，所以声明float类型的变量需要后面加'f'或'F'，或者使用(float)num转换类型
        float a = 68399.22f;
        float b = (float) 124052.96;
        System.out.println(a + b); // 出现精度问题，结果错误
        BigDecimal bigDecimal1 = new BigDecimal(Float.toString(a));
        BigDecimal bigDecimal2 = new BigDecimal(Float.toString(b));
        BigDecimal result = bigDecimal1.add(bigDecimal2);
        System.out.println(result.toString()); // 正确
    }
}
