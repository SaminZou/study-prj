package basic.q3;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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
        BigDecimal c = new BigDecimal(3);

        // 比较 compareTo ： -1 小于； 0 等于； 1 大于
        // -1
        System.out.println(b.compareTo(a));
        // 0
        System.out.println(a.compareTo(c));
        // 1
        System.out.println(a.compareTo(b));

        // 加法 add
        // 5
        System.out.println(a.add(b));

        // 减法 subtract
        // 1
        System.out.println(a.subtract(b));

        // 乘法 multiply
        // 6
        System.out.println(a.multiply(b));

        // 除法 divide
        // 1
        System.out.println(a.divide(b, BigDecimal.ROUND_HALF_UP));

        // 处理小数 setScale()
        BigDecimal d = new BigDecimal("1.234");
        // 1.23 四舍五入
        System.out.println(d.setScale(2, BigDecimal.ROUND_HALF_UP));

        // 求余 divideAndRemainder
        // [1 , 1] 第一位为商，第二位为余数
        System.out.println(Arrays.toString(a.divideAndRemainder(b)));

        // 表示显示两位小数
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        System.out.println(decimalFormat.format(new BigDecimal("1234.1234")));
        System.out.println(decimalFormat.format(1234.1234));
        // 输出是 .123 DecimalFormat decimalFormat = new DecimalFormat("0.00"); 来显示 0
        System.out.println(decimalFormat.format(0.123));

        // 建立货币格式化引用
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        // 建立百分比格式化引用
        NumberFormat percent = NumberFormat.getPercentInstance();
        // 百分比小数点最多3位
        percent.setMaximumFractionDigits(3);
        // 贷款金额
        BigDecimal loanAmount = new BigDecimal("15000.48");
        // 利率
        BigDecimal interestRate = new BigDecimal("0.008");
        // 相乘
        BigDecimal interest = loanAmount.multiply(interestRate);
        System.out.println("贷款金额:\t" + currency.format(loanAmount));
        System.out.println("利率:\t" + percent.format(interestRate));
        System.out.println("利息:\t" + currency.format(interest));
    }
}
