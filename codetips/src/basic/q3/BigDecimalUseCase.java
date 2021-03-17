package basic.q3;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;

/**
 * BigDecimal 类用例
 *
 * @author samin
 * @date 2020-12-31
 */
public class BigDecimalUseCase {

    public static void main(String[] args) {
        BigDecimal a = new BigDecimal(3);
        BigDecimal b = new BigDecimal(2);

        // 比较 compareTo ： -1 小于； 0 等于； 1 大于
        // 1
        System.out.println(a.compareTo(b));
        // 0
        System.out.println(a.compareTo(a));
        // -1
        System.out.println(b.compareTo(a));

        // 加法 add
        // 5
        System.out.println(a.add(b).toString());

        // 减法 subtract
        // 1
        System.out.println(a.subtract(b));

        // 乘法 multiply
        // 6
        System.out.println(a.multiply(b).toString());

        // 除法 divide
        // 1
        System.out.println(a.divide(b).toString());

        // 处理小数 setScale()
        BigDecimal c = new BigDecimal("1.234");
        // 1.23 四舍五入
        System.out.println(c.setScale(2, BigDecimal.ROUND_HALF_UP));

        // 求余 divideAndRemainder
        // [1 , 1] 第一位为商，第二位为余数
        System.out.println(Arrays.toString(a.divideAndRemainder(b)));

        // 表示显示两位小数
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        System.out.println(decimalFormat.format(new BigDecimal("1234.1234")));
        System.out.println(decimalFormat.format(1234.1234));
    }
}
