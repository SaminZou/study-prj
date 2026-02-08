package basic;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 浮点数运算精度问题演示与解决方案
 * 
 * <p>本类展示了浮点数运算中常见的精度问题，并提供了使用 BigDecimal 的解决方案。</p>
 * 
 * <h3>常见浮点数精度问题：</h3>
 * <ul>
 *   <li>浮点数相加时的精度丢失</li>
 *   <li>浮点数相减时的精度问题</li>
 *   <li>浮点数乘除运算的精度误差累积</li>
 *   <li>浮点数比较时的精度陷阱</li>
 * </ul>
 * 
 * <h3>解决方案：</h3>
 * <ul>
 *   <li>使用 BigDecimal 进行精确计算</li>
 *   <li>使用字符串构造 BigDecimal 避免精度丢失</li>
 *   <li>设置合适的精度和舍入模式</li>
 * </ul>
 *
 * @author samin
 * @date 2020-12-31
 * @version 2.0
 */
public class FloatCalcError {
    
    /**
     * 演示浮点数精度问题及解决方案
     */
    public static void main(String[] args) {
        System.out.println("=== 浮点数精度问题演示 ===\n");
        
        // 示例1：浮点数相加精度问题
        demonstrateFloatAdditionError();
        
        // 示例2：浮点数减法精度问题
        demonstrateFloatSubtractionError();
        
        // 示例3：浮点数乘法精度问题
        demonstrateFloatMultiplicationError();
        
        // 示例4：浮点数比较精度陷阱
        demonstrateFloatComparisonTrap();
        
        // 示例5：金融计算中的精度问题
        demonstrateFinancialCalculation();
    }
    
    /**
     * 演示浮点数相加时的精度问题
     */
    private static void demonstrateFloatAdditionError() {
        System.out.println("1. 浮点数相加精度问题：");
        
        // 小数默认为 double 类型，声明 float 类型需要加 f 或 F
        float a = 68399.22F;
        float b = (float) 124052.96;
        
        System.out.println("原始值：");
        System.out.println("  a = " + a);
        System.out.println("  b = " + b);
        
        // 浮点数相加 - 出现精度问题
        float floatResult = a + b;
        System.out.println("浮点数相加结果：" + floatResult);
        System.out.println("预期结果：" + (68399.22 + 124052.96));
        System.out.println("误差：" + (floatResult - (68399.22 + 124052.96)));
        
        // 使用 BigDecimal 解决精度问题
        BigDecimal bigDecimal1 = new BigDecimal(Float.toString(a));
        BigDecimal bigDecimal2 = new BigDecimal(Float.toString(b));
        BigDecimal bigDecimalResult = bigDecimal1.add(bigDecimal2);
        
        System.out.println("BigDecimal 相加结果：" + bigDecimalResult);
        System.out.println("--------------------------------------\n");
    }
    
    /**
     * 演示浮点数减法精度问题
     */
    private static void demonstrateFloatSubtractionError() {
        System.out.println("2. 浮点数减法精度问题：");
        
        float c = 10.1F;
        float d = 9.93F;
        
        System.out.println("原始值：");
        System.out.println("  c = " + c);
        System.out.println("  d = " + d);
        
        // 浮点数相减 - 精度问题
        float floatSubtract = c - d;
        System.out.println("浮点数相减结果：" + floatSubtract);
        System.out.println("预期结果：0.17");
        System.out.println("实际结果：" + floatSubtract);
        
        // BigDecimal 解决方案
        BigDecimal bdC = new BigDecimal(Float.toString(c));
        BigDecimal bdD = new BigDecimal(Float.toString(d));
        BigDecimal bdSubtract = bdC.subtract(bdD);
        
        System.out.println("BigDecimal 相减结果：" + bdSubtract);
        System.out.println("--------------------------------------\n");
    }
    
    /**
     * 演示浮点数乘法精度问题
     */
    private static void demonstrateFloatMultiplicationError() {
        System.out.println("3. 浮点数乘法精度问题：");
        
        float price = 0.1F;
        int quantity = 7;
        
        System.out.println("原始值：");
        System.out.println("  单价：" + price);
        System.out.println("  数量：" + quantity);
        
        // 浮点数乘法 - 精度问题
        float total = price * quantity;
        System.out.println("浮点数乘法结果：" + total);
        System.out.println("预期结果：0.7");
        
        // BigDecimal 解决方案
        BigDecimal bdPrice = new BigDecimal(Float.toString(price));
        BigDecimal bdQuantity = new BigDecimal(quantity);
        BigDecimal bdTotal = bdPrice.multiply(bdQuantity);
        
        System.out.println("BigDecimal 乘法结果：" + bdTotal);
        System.out.println("--------------------------------------\n");
    }
    
    /**
     * 演示浮点数比较精度陷阱
     */
    private static void demonstrateFloatComparisonTrap() {
        System.out.println("4. 浮点数比较精度陷阱：");
        
        float e = 0.1F + 0.2F;
        float f = 0.3F;
        
        System.out.println("原始值：");
        System.out.println("  e = 0.1 + 0.2 = " + e);
        System.out.println("  f = 0.3");
        
        // 直接比较 - 错误的方式
        boolean directCompare = (e == f);
        System.out.println("直接比较 (e == f)：" + directCompare);
        
        // 使用容差比较 - 正确的方式
        float tolerance = 0.0001F;
        boolean toleranceCompare = Math.abs(e - f) < tolerance;
        System.out.println("容差比较 (|e - f| < " + tolerance + ")：" + toleranceCompare);
        
        // BigDecimal 比较 - 最准确的方式
        BigDecimal bdE = new BigDecimal(Float.toString(e));
        BigDecimal bdF = new BigDecimal(Float.toString(f));
        boolean bdCompare = bdE.compareTo(bdF) == 0;
        System.out.println("BigDecimal 比较：" + bdCompare);
        System.out.println("--------------------------------------\n");
    }
    
    /**
     * 演示金融计算中的精度问题
     */
    private static void demonstrateFinancialCalculation() {
        System.out.println("5. 金融计算中的精度问题：");
        
        // 模拟银行利息计算
        double principal = 10000.0; // 本金
        double annualRate = 0.035;  // 年利率 3.5%
        int years = 5;              // 年限
        
        System.out.println("金融计算参数：");
        System.out.println("  本金：" + principal);
        System.out.println("  年利率：" + annualRate);
        System.out.println("  年限：" + years);
        
        // 浮点数计算 - 可能产生精度误差
        double floatInterest = principal * Math.pow(1 + annualRate, years) - principal;
        System.out.println("浮点数计算利息：" + floatInterest);
        
        // BigDecimal 计算 - 精确结果
        BigDecimal bdPrincipal = new BigDecimal(Double.toString(principal));
        BigDecimal bdRate = new BigDecimal(Double.toString(annualRate));
        BigDecimal bdOne = new BigDecimal("1");
        
        // 计算复利：本金 * (1 + 利率)^年数
        BigDecimal compoundFactor = bdOne.add(bdRate).pow(years);
        BigDecimal bdTotal = bdPrincipal.multiply(compoundFactor);
        BigDecimal bdInterest = bdTotal.subtract(bdPrincipal);
        
        // 设置精度为2位小数，银行舍入模式
        bdInterest = bdInterest.setScale(2, RoundingMode.HALF_UP);
        
        System.out.println("BigDecimal 计算利息：" + bdInterest);
        System.out.println("--------------------------------------\n");
        
        System.out.println("=== 总结 ===");
        System.out.println("• 浮点数运算存在精度问题，不适合金融和精确计算");
        System.out.println("• 使用 BigDecimal 可以避免精度丢失");
        System.out.println("• 构造 BigDecimal 时推荐使用字符串参数");
        System.out.println("• 设置合适的精度和舍入模式很重要");
    }
}
