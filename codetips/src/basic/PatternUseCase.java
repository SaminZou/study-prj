package basic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式使用示例
 *
 * 本类展示了Java中Pattern和Matcher类的使用方法，包含各种正则表达式语法和常见用例
 *
 * @author samin
 * @date 2021-01-10
 * @version 2.0
 */
public class PatternUseCase {

    public static void main(String[] args) {
        System.out.println("=== 正则表达式基础用例演示 ===\n");

        // 1. 基础演示：特殊字符匹配
        demonstrateBasicPatternMatching();

        // 2. 常见字符类演示
        demonstrateCharacterClasses();

        // 3. 量词和重复演示
        demonstrateQuantifiers();

        // 4. 分组和引用演示
        demonstrateGroupsAndReferences();

        // 5. 边界匹配演示
        demonstrateBoundaryMatching();

        // 6. 实际应用场景演示
        demonstratePracticalUseCases();
    }

    /**
     * 1. 基础演示：特殊字符匹配
     */
    private static void demonstrateBasicPatternMatching() {
        System.out.println("1. 基础演示：特殊字符匹配");
        System.out.println("-".repeat(50));

        // 演示特殊字符匹配
        String str = "822085977@!qq.(com)";
        String regEx = "[`~!#$%^&*()+=|{}':;',\\[\\]<>/?~！#￥%……&*（）——+|{}【】‘；：”“’。，、？]";

        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(str);

        System.out.println("原始字符串: " + str);
        System.out.print("匹配到的特殊字符: ");
        while (matcher.find()) {
            System.out.print(matcher.group() + " ");
        }
        System.out.println("\n");
    }

    /**
     * 2. 常见字符类演示
     */
    private static void demonstrateCharacterClasses() {
        System.out.println("2. 常见字符类演示");
        System.out.println("-".repeat(50));

        // 数字相关
        testPattern("\\d", "数字匹配", "123", "abc");
        testPattern("\\D", "非数字匹配", "abc", "123");
        testPattern("[0-9]", "数字范围", "5", "a");

        // 字母和单词相关
        testPattern("\\w", "单词字符", "a1_", "@#");
        testPattern("\\W", "非单词字符", "@#", "a1_");
        testPattern("[a-zA-Z]", "字母", "aBc", "123");

        // 空白字符相关
        testPattern("\\s", "空白字符", " \t\n", "abc");
        testPattern("\\S", "非空白字符", "abc", " \t");

        // 任意字符
        testPattern(".", "任意字符", "a1@ ", "\n");

        System.out.println();
    }

    /**
     * 3. 量词和重复演示
     */
    private static void demonstrateQuantifiers() {
        System.out.println("3. 量词和重复演示");
        System.out.println("-".repeat(50));

        // 零次或一次
        testPattern("a?", "零次或一次", "", "a", "aa");

        // 一次或多次
        testPattern("a+", "一次或多次", "a", "aaa", "");

        // 零次或多次
        testPattern("a*", "零次或多次", "", "a", "aaa");

        // 精确次数
        testPattern("a{3}", "精确3次", "aaa", "aa", "aaaa");

        // 范围次数
        testPattern("a{2,4}", "2-4次", "aa", "aaa", "aaaaa");

        // 至少次数
        testPattern("a{2,}", "至少2次", "aa", "aaa", "a");

        System.out.println();
    }

    /**
     * 4. 分组和引用演示
     */
    private static void demonstrateGroupsAndReferences() {
        System.out.println("4. 分组和引用演示");
        System.out.println("-".repeat(50));

        // 基本分组
        testPattern("(ab)+", "分组重复", "abab", "ab", "a");

        // 或运算
        testPattern("a|b", "或运算", "a", "b", "c");
        testPattern("(cat|dog)", "单词或", "cat", "dog", "bird");

        // 反向引用
        testPattern("(\\d)\\1", "反向引用", "11", "22", "12");
        testPattern("(\\w)\\1{2}", "重复3次", "aaa", "bbb", "abc");

        System.out.println();
    }

    /**
     * 5. 边界匹配演示
     */
    private static void demonstrateBoundaryMatching() {
        System.out.println("5. 边界匹配演示");
        System.out.println("-".repeat(50));

        // 行首匹配
        testPattern("^abc", "行首匹配", "abc123", "123abc");

        // 行尾匹配
        testPattern("xyz$", "行尾匹配", "123xyz", "xyz123");

        // 单词边界
        testPattern("\\bword\\b", "单词边界", "word", "keyword");
        testPattern("\\Bword\\B", "非单词边界", "keyword", "word");

        System.out.println();
    }

    /**
     * 6. 实际应用场景演示
     */
    private static void demonstratePracticalUseCases() {
        System.out.println("6. 实际应用场景演示");
        System.out.println("-".repeat(50));

        // 邮箱验证
        testPattern("^[\\w.-]+@[\\w.-]+\\.[a-z]{2,}$", "邮箱验证", "test@example.com", "invalid.email");

        // 手机号验证（中国）
        testPattern("^1[3-9]\\d{9}$", "手机号验证", "13800138000", "12345678901");

        // URL验证
        testPattern("^(https?://)?([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$", "URL验证", "https://www.example.com",
                    "invalid.url");

        // 身份证验证（简化版）
        testPattern("^[1-9]\\d{5}(18|19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])\\d{3}[0-9Xx]$", "身份证验证",
                    "110101199001011234", "123456789012345678");

        // 日期格式验证
        testPattern("^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$", "日期验证", "2023-12-25", "2023-13-01");

        // 密码强度验证（8-16位，包含字母和数字）
        testPattern("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*#?&]{8,16}$", "密码验证", "Password123", "weak");

        // 提取HTML标签内容
        String html = "<div>Hello World</div><p>This is a paragraph</p>";
        Pattern tagPattern = Pattern.compile("<([a-z][a-z0-9]*)[^>]*>(.*?)</\\1>");
        Matcher tagMatcher = tagPattern.matcher(html);

        System.out.println("HTML标签提取:");
        while (tagMatcher.find()) {
            System.out.println("标签: " + tagMatcher.group(1) + ", 内容: " + tagMatcher.group(2));
        }

        System.out.println();
    }

    /**
     * 测试正则表达式模式
     *
     * @param pattern 正则表达式
     * @param description 描述
     * @param testStrings 测试字符串
     */
    private static void testPattern(String pattern, String description, String... testStrings) {
        System.out.println("模式: " + pattern + " (" + description + ")");
        for (String testStr : testStrings) {
            boolean matches = Pattern.matches(pattern, testStr);
            System.out.println("  \"" + testStr + "\" -> " + matches);
        }
        System.out.println();
    }
}
