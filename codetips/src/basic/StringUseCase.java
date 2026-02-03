package basic;

import java.util.Collections;
import java.util.StringJoiner;

/**
 * String 类用例
 *
 * 本类展示了 Java 中 String 类的常见用法和最佳实践
 *
 * @author samin
 * @date 2020-12-31
 */
public class StringUseCase {

    // ==================== 常量定义 ====================
    private static final String CONSTANT_POOL_STRING = "123";

    // ==================== 空白字符处理 ====================
    private static final String WHITESPACE_EXAMPLE = " Hello \n World \t ! ";
    private static final String DOT_SEPARATOR = "\\.";
    private static final String PIPE_SEPARATOR = "\\|";
    private static final String MULTIPLE_SEPARATOR = "@|&";

    public static void main(String[] args) {
        whitespaceHandling();
        stringBuilderUsage();
        stringJoinerUsage();
        constantPoolDemo();
        safeStringComparison();
        stringSplitting();
        stringFormatting();
        basicStringOperations();
        stringConversions();
    }

    /**
     * 空白字符处理
     * 展示如何去除字符串中的不同类型空白字符
     */
    private static void whitespaceHandling() {
        System.out.println("===== 空白字符处理 =====");

        // 去除所有空格（仅空格字符）
        String onlySpacesRemoved = WHITESPACE_EXAMPLE.replace(" ", "");
        System.out.println("去除所有空格: " + onlySpacesRemoved);

        // 去除所有空白字符（空格、换行符、制表符等）
        // \s+ 匹配一个或多个空白字符
        String allWhitespaceRemoved = WHITESPACE_EXAMPLE.replaceAll("\\s+", "");
        System.out.println("去除所有空白: " + allWhitespaceRemoved);

        // 去除首尾空白（推荐方式）
        String trimmed = WHITESPACE_EXAMPLE.trim();
        System.out.println("去除首尾空白: " + trimmed);

        // JDK 11+ 提供更强大的 strip 方法（可识别全角空格等 Unicode 空白）
        // String stripped = WHITESPACE_EXAMPLE.strip();

        System.out.println();
    }

    /**
     * StringBuilder 使用
     * 展示高效的字符串拼接方式
     */
    private static void stringBuilderUsage() {
        System.out.println("===== StringBuilder 使用 =====");

        // 链式调用更简洁
        StringBuilder sb = new StringBuilder()
                .append("hello ")
                .append("world");

        // insert 在指定位置插入
        sb.insert(0, " ").insert(0, "always");

        System.out.println("拼接结果: " + sb);
        System.out.println();
    }

    /**
     * StringJoiner 使用
     * JDK 8+ 提供的高效字符串连接工具
     */
    private static void stringJoinerUsage() {
        System.out.println("===== StringJoiner 使用 =====");

        // 空列表拼接
        System.out.println("空列表拼接: " + String.join(",", Collections.emptyList()));

        // 使用 StringJoiner 添加分隔符
        StringJoiner sj = new StringJoiner(" ");
        sj.add("hello").add("world").add("!");
        System.out.println("StringJoiner 拼接: " + sj);

        // 使用 String.join() 静态方法（更简洁）
        String joined = String.join(" ", "hello", "world", "!");
        System.out.println("String.join() 拼接: " + joined);

        // 带前后缀的拼接（如路径拼接）
        sj = new StringJoiner("/", "/", "");
        sj.add("usr").add("local").add("jdk").add("bin");
        System.out.println("路径拼接: " + sj);

        // 性能说明：
        // String.join() 底层使用 StringJoiner
        // StringJoiner 内部使用 StringBuilder
        // 因此拼接效率很高，推荐使用

        System.out.println();
    }

    /**
     * 字符串常量池演示
     * 展示字符串在常量池中的行为
     */
    private static void constantPoolDemo() {
        System.out.println("===== 字符串常量池 =====");

        String a = "123"; // 字面量，存储在常量池

        // 以下三个引用指向常量池中的同一个对象
        System.out.println("\"123\" hash: " + System.identityHashCode("123"));
        System.out.println("ABC hash: " + System.identityHashCode(CONSTANT_POOL_STRING));
        System.out.println("a hash: " + System.identityHashCode(a));
        System.out.println("三者是否相同: " +
                (System.identityHashCode("123") == System.identityHashCode(CONSTANT_POOL_STRING)));

        System.out.println();
    }

    /**
     * 安全的字符串比较
     * 避免 NPE 的最佳实践
     */
    private static void safeStringComparison() {
        System.out.println("===== 安全字符串比较 =====");

        String a = "123";
        String nullString = null;

        // 错误写法：可能抛出 NullPointerException
        // if (nullString.equals("123")) { ... }

        // 正确写法：使用常量调用 equals
        boolean result1 = "123".equals(a);
        System.out.println("\"123\".equals(a): " + result1);

        boolean result2 = "123".equals(nullString);
        System.out.println("\"123\".equals(null): " + result2);

        // 推荐：使用 Objects.equals() (JDK 7+)
        // boolean result3 = Objects.equals(nullString, "123");

        System.out.println();
    }

    /**
     * 字符串分割
     * 展示常见分隔符的处理方式
     */
    private static void stringSplitting() {
        System.out.println("===== 字符串分割 =====");

        // 按 "." 分割（需要转义）
        String ip = "192.168.110.1";
        System.out.println("IP 分割:");
        for (String part : ip.split(DOT_SEPARATOR)) {
            System.out.println("  " + part);
        }

        // 无分隔符的情况
        String single = "192";
        System.out.println("无分隔符分割:");
        for (String part : single.split(DOT_SEPARATOR)) {
            System.out.println("  " + part);
        }

        // 按 "|" 分割（需要转义）
        String pipeSeparated = "aa|bb|cc|dd";
        System.out.println("管道符分割:");
        for (String part : pipeSeparated.split(PIPE_SEPARATOR)) {
            System.out.println("  " + part);
        }

        // 多个分隔符（使用 | 连接）
        String multiSeparated = "1@2@3&4&5&6";
        System.out.println("多分隔符分割:");
        for (String part : multiSeparated.split(MULTIPLE_SEPARATOR)) {
            System.out.println("  " + part);
        }

        System.out.println();
    }

    /**
     * 字符串格式化
     */
    private static void stringFormatting() {
        System.out.println("===== 字符串格式化 =====");

        String formatted = String.format("%s现在的价格是： %d$", "比特币", 44000);
        System.out.println(formatted);

        // 常用格式占位符：
        // %s - 字符串
        // %d - 整数
        // %f - 浮点数
        // %.2f - 保留两位小数
        // %x - 十六进制
        // %b - 布尔值

        System.out.println();
    }

    /**
     * 基础字符串操作
     * 展示常用的字符串方法
     */
    private static void basicStringOperations() {
        System.out.println("===== 基础字符串操作 =====");

        String text = "Hello World Java";

        // 长度
        System.out.println("长度: " + text.length());

        // 大小写转换
        System.out.println("转大写: " + text.toUpperCase());
        System.out.println("转小写: " + text.toLowerCase());

        // 判断是否包含
        System.out.println("包含 \"World\": " + text.contains("World"));

        // 获取索引
        System.out.println("\"World\" 索引: " + text.indexOf("World"));
        System.out.println("最后一个 'o': " + text.lastIndexOf("o"));

        // 截取
        System.out.println("前5个字符: " + text.substring(0, 5));
        System.out.println("从6开始: " + text.substring(6));

        // 替换
        System.out.println("替换 World -> Java: " + text.replace("World", "Java"));

        // 判断前缀和后缀
        System.out.println("以 \"Hello\" 开头: " + text.startsWith("Hello"));
        System.out.println("以 \"Java\" 结尾: " + text.endsWith("Java"));

        // 重复字符串 (JDK 11+)
        // System.out.println("重复3次: " + "Hi ".repeat(3));

        System.out.println();
    }

    /**
     * 字符串转换
     * 展示字符串与基本类型的转换
     */
    private static void stringConversions() {
        System.out.println("===== 字符串转换 =====");

        // 字符串转数字
        String numStr = "12345";
        int intValue = Integer.parseInt(numStr);
        long longValue = Long.parseLong(numStr);
        double doubleValue = Double.parseDouble("3.14");

        System.out.println("字符串转int: " + intValue);
        System.out.println("字符串转long: " + longValue);
        System.out.println("字符串转double: " + doubleValue);

        // 数字转字符串
        String fromInt = String.valueOf(100);
        String fromDouble = String.valueOf(3.14159);

        System.out.println("int转字符串: " + fromInt);
        System.out.println("double转字符串: " + fromDouble);

        // 字符数组与字符串互转
        char[] chars = {'H', 'e', 'l', 'l', 'o'};
        String fromChars = new String(chars);
        char[] toChars = fromChars.toCharArray();

        System.out.println("字符数组转字符串: " + fromChars);
        System.out.println("字符串转字符数组: " + new String(toChars));

        // 判断空字符串
        String empty = "";
        String blank = "   ";
        String normal = "hello";

        System.out.println("empty 是否为空: " + empty.isEmpty());
        System.out.println("blank 是否为空: " + blank.isEmpty());
        System.out.println("blank 是否为空白: " + blank.isBlank()); // JDK 11+
        System.out.println("normal 是否为空: " + normal.isEmpty());

        System.out.println();
    }
}
