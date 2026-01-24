package basic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;

/**
 * BigDecimal Use Case - Optimized Version
 * Demonstrates correct usage patterns and best practices for BigDecimal
 *
 * @author samin
 * @date 2020-12-31
 * @version 2.0 optimized
 */
public class BigDecimalUseCase {

    private static final int DEFAULT_SCALE = 10;
    private static final int CURRENCY_SCALE = 2;
    private static final RoundingMode DEFAULT_ROUNDING = RoundingMode.HALF_UP;
    
    private static final BigDecimal DECIMAL_3 = new BigDecimal(3);
    private static final BigDecimal DECIMAL_2 = new BigDecimal(2);
    private static final BigDecimal DECIMAL_1100 = BigDecimal.valueOf(1100);
    private static final BigDecimal DECIMAL_32 = BigDecimal.valueOf(32);
    private static final BigDecimal DECIMAL_100 = BigDecimal.valueOf(100);
    private static final BigDecimal DECIMAL_16 = BigDecimal.valueOf(16);
    private static final BigDecimal DECIMAL_1000 = BigDecimal.valueOf(1000);

    public static void main(String[] args) {
        System.out.println("=== BigDecimal 优化用例演示 ===\n");
        
        demonstratePrecisionIssues();
        demonstrateBasicOperations();
        demonstrateComparison();
        demonstrateScaling();
        demonstrateRemainder();
        demonstrateFormatting();
        demonstrateFinancialCalculations();
        
        System.out.println("\n=== 演示完成 ===");
    }

    private static void demonstratePrecisionIssues() {
        System.out.println("1. Precision Issues:");
        
        BigDecimal incorrectResult = calculateChainWithoutPrecision();
        System.out.println("   Incorrect (no scale): " + incorrectResult);
        
        BigDecimal correctResult = calculateChainWithPrecision();
        System.out.println("   Correct (with scale): " + correctResult);
        
        System.out.println();
    }

    private static void demonstrateBasicOperations() {
        System.out.println("2. Basic Arithmetic Operations:");
        
        System.out.println("   Addition (3 + 2): " + safeAdd(DECIMAL_3, DECIMAL_2));
        System.out.println("   Subtraction (3 - 2): " + safeSubtract(DECIMAL_3, DECIMAL_2));
        System.out.println("   Multiplication (3 * 2): " + safeMultiply(DECIMAL_3, DECIMAL_2));
        System.out.println("   Division (3 / 2): " + safeDivide(DECIMAL_3, DECIMAL_2));
        
        System.out.println();
    }

    private static void demonstrateComparison() {
        System.out.println("3. Comparison Operations:");
        
        BigDecimal a = DECIMAL_3;
        BigDecimal b = DECIMAL_2;
        BigDecimal c = DECIMAL_3;
        
        System.out.println("   2 < 3: " + (b.compareTo(a) < 0 ? "true" : "false"));
        System.out.println("   3 = 3: " + (a.compareTo(c) == 0 ? "true" : "false"));
        System.out.println("   3 > 2: " + (a.compareTo(b) > 0 ? "true" : "false"));
        
        System.out.println();
    }

    private static void demonstrateScaling() {
        System.out.println("4. Scaling Operations:");
        
        BigDecimal value = new BigDecimal("1.234");
        System.out.println("   Original: " + value);
        System.out.println("   Scale to 2: " + safeSetScale(value, 2));
        System.out.println("   Scale to 3: " + safeSetScale(value, 3));
        
        System.out.println();
    }

    private static void demonstrateRemainder() {
        System.out.println("5. Remainder Operations:");
        
        BigDecimal[] result = safeDivideAndRemainder(DECIMAL_3, DECIMAL_2);
        System.out.println("   3 ÷ 2 = Quotient: " + result[0] + ", Remainder: " + result[1]);
        
        System.out.println();
    }

    private static void demonstrateFormatting() {
        System.out.println("6. Formatting Examples:");
        
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        DecimalFormat decimalFormatWithZero = new DecimalFormat("0.00");
        
        BigDecimal value1 = new BigDecimal("1234.1234");
        BigDecimal value2 = new BigDecimal("0.123");
        
        System.out.println("   1234.1234 format (#.00): " + decimalFormat.format(value1));
        System.out.println("   0.123 format (#.00): " + decimalFormat.format(value2));
        System.out.println("   0.123 format (0.00): " + decimalFormatWithZero.format(value2));
        
        System.out.println();
    }

    private static void demonstrateFinancialCalculations() {
        System.out.println("7. Financial Calculations:");
        
        BigDecimal loanAmount = new BigDecimal("15000.48");
        BigDecimal interestRate = new BigDecimal("0.008");
        BigDecimal interest = calculateInterest(loanAmount, interestRate);
        
        System.out.println("   Loan Amount: " + formatCurrency(loanAmount));
        System.out.println("   Interest Rate: " + formatPercentage(interestRate));
        System.out.println("   Interest: " + formatCurrency(interest));
        
        System.out.println();
    }

    

    private static BigDecimal safeAdd(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }

    private static BigDecimal safeSubtract(BigDecimal a, BigDecimal b) {
        return a.subtract(b);
    }

    private static BigDecimal safeMultiply(BigDecimal a, BigDecimal b) {
        return a.multiply(b);
    }

    private static BigDecimal safeDivide(BigDecimal dividend, BigDecimal divisor) {
        return safeDivide(dividend, divisor, DEFAULT_SCALE, DEFAULT_ROUNDING);
    }

    private static BigDecimal safeDivide(BigDecimal dividend, BigDecimal divisor, int scale, RoundingMode roundingMode) {
        if (divisor.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("Divisor cannot be zero");
        }
        return dividend.divide(divisor, scale, roundingMode);
    }

    private static BigDecimal safeSetScale(BigDecimal value, int scale) {
        return value.setScale(scale, DEFAULT_ROUNDING);
    }

    private static BigDecimal[] safeDivideAndRemainder(BigDecimal dividend, BigDecimal divisor) {
        if (divisor.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("Divisor cannot be zero");
        }
        return dividend.divideAndRemainder(divisor);
    }

    private static BigDecimal calculateChainWithoutPrecision() {
        return DECIMAL_1100
                .divide(DECIMAL_32, DEFAULT_ROUNDING)
                .divide(DECIMAL_100, DEFAULT_ROUNDING)
                .multiply(DECIMAL_16)
                .divide(DECIMAL_1000, DEFAULT_ROUNDING);
    }

    private static BigDecimal calculateChainWithPrecision() {
        return DECIMAL_1100
                .divide(DECIMAL_32, DEFAULT_SCALE, DEFAULT_ROUNDING)
                .divide(DECIMAL_100, DEFAULT_SCALE, DEFAULT_ROUNDING)
                .multiply(DECIMAL_16)
                .divide(DECIMAL_1000, DEFAULT_SCALE, DEFAULT_ROUNDING);
    }

    private static BigDecimal calculateInterest(BigDecimal principal, BigDecimal rate) {
        return safeMultiply(principal, rate);
    }

    private static String formatCurrency(BigDecimal amount) {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(amount);
    }

    private static String formatPercentage(BigDecimal rate) {
        NumberFormat percent = NumberFormat.getPercentInstance();
        percent.setMaximumFractionDigits(3);
        return percent.format(rate);
    }
}
