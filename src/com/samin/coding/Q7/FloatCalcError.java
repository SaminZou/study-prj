package com.samin.coding.Q7;

import java.math.BigDecimal;

/**
 * double和float精度不准确的问题 使用java.math.BigDecimal解决
 *
 * @author samin
 * @date 2020-12-31
 */
public class FloatCalcError {

    public static void main(String[] args) {
        // 小数默认为double类型，所以声明float类型的变量需要后面加'f'或'F'，或者使用(float)num转换类型
        float a = 68399.22f;
        float b = (float) 124052.96;
        // 出现精度问题，结果错误
        System.out.println(a + b);

        System.out.println();

        BigDecimal bigDecimal1 = new BigDecimal(Float.toString(a));
        BigDecimal bigDecimal2 = new BigDecimal(Float.toString(b));
        BigDecimal result = bigDecimal1.add(bigDecimal2);
        // 正确
        System.out.println(result.toString());
    }
}
