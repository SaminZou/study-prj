package basic;

/**
 * 原生类型的坑 - 基本类型的包装类常量池使用案例
 *
 * 本类演示了Java中基本类型包装类的常量池机制以及相关的坑点：
 * 1. Integer等包装类的缓存机制 (-128到127)
 * 2. 自动拆装箱的行为
 * 3. String常量池的机制
 * 4. equals与==的区别
 *
 * @author samin
 * @date 2021-01-10
 */
public class PrimitiveTypeUseCase {

    public static final String STRING1;
    public static final String STRING2;
    public static final String STRING3 = "a";
    public static final String STRING4 = "a";

    static {
        STRING1 = "a";
        STRING2 = "a";
    }

    public static void main(String[] args) {
        demonstrateWrapperCache();
        demonstrateAutoBoxingUnboxing();
        demonstrateStringConstantPool();
        demonstrateStringIntern();
        demonstrateLongEqualsIssue();
    }

    /**
     * 演示包装类缓存机制
     *
     * Byte, Short, Integer, Long, Character, Boolean
     * 对于数值在[-128, 127]范围内的包装类对象会使用缓存
     * 超出此范围会创建新对象
     *
     * 最佳实践：始终使用equals()方法进行对象比较
     */
    private static void demonstrateWrapperCache() {
        System.out.println("=== 包装类缓存机制演示 ===");

        // 缓存范围内的Integer - 使用同一个对象
        Integer cachedInt1 = 100;
        Integer cachedInt2 = 100;
        System.out.printf("Integer缓存比较(%d == %d): %b%n", cachedInt1, cachedInt2, cachedInt1 == cachedInt2);

        // 超出缓存范围的Integer - 创建不同对象
        Integer uncachedInt1 = 400;
        Integer uncachedInt2 = 400;
        System.out.printf("Integer非缓存比较(%d == %d): %b%n", uncachedInt1, uncachedInt2, uncachedInt1 == uncachedInt2);

        // Short缓存范围也是-128到127，128超出范围
        Short short1 = 128;
        Short short2 = 128;
        System.out.printf("Short缓存比较(%d == %d): %b%n", short1, short2, short1 == short2);

        System.out.println();
    }

    /**
     * 演示自动拆装箱行为
     *
     * 包装类与基本类型进行算术运算时会自动拆箱
     * 包装类之间的算术运算也会自动拆箱
     */
    private static void demonstrateAutoBoxingUnboxing() {
        System.out.println("=== 自动拆装箱演示 ===");

        int primitiveValue = 300;
        // 注意：避免使用已废弃的构造方法，使用valueOf()替代
        Integer boxedValue1 = Integer.valueOf(300);
        Integer boxedValue2 = Integer.valueOf(0);

        // 自动拆箱：包装类参与运算时自动转换为基本类型
        boolean result1 = primitiveValue == (boxedValue1 + boxedValue2);
        boolean result2 = 300 == (boxedValue1 + boxedValue2);

        System.out.printf("基本类型 == 包装类运算结果: %b%n", result1);
        System.out.printf("字面量 == 包装类运算结果: %b%n", result2);

        System.out.println();
    }

    /**
     * 演示String常量池机制
     *
     * 1. 编译期确定的字符串字面量会放入常量池
     * 2. 编译期可确定的字符串连接也会放入常量池
     * 3. 运行时确定的字符串连接不会放入常量池
     */
    private static void demonstrateStringConstantPool() {
        System.out.println("=== String常量池演示 ===");

        String part1 = "str";
        String part2 = "ing";

        // 编译期确定的字符串连接 - 进入常量池
        String compileTimeConcat = "str" + "ing";
        String literalString = "string";

        // 运行时的字符串连接 - 不进入常量池
        String runtimeConcat = part1 + part2;

        System.out.printf("运行时连接结果 == 字面量: %b%n", runtimeConcat == literalString);
        System.out.printf("编译期连接结果 == 字面量: %b%n", compileTimeConcat == literalString);

        // 静态变量的字符串连接测试
        String testString = "aa";
        System.out.printf("静态变量运行时连接 == 字面量: %b%n", testString == (STRING1 + STRING2));
        System.out.printf("静态常量编译期连接 == 字面量: %b%n", testString == (STRING3 + STRING4));

        System.out.println();
    }

    /**
     * 演示String.intern()方法
     *
     * intern()方法的作用：
     * 1. 如果常量池中已存在相同内容的字符串，返回常量池中的引用
     * 2. 如果常量池中不存在，在常量池中创建并返回引用
     */
    private static void demonstrateStringIntern() {
        System.out.println("=== String.intern()演示 ===");

        String heapString = "计算机";
        String internedString = heapString.intern();
        String constantPoolString = "计算机";

        System.out.printf("堆字符串 == intern结果: %b (堆对象 vs 常量池对象)%n", heapString == internedString);
        System.out.printf("常量池字符串 == intern结果: %b (都是常量池对象)%n", constantPoolString == internedString);

        System.out.println();

        // 思考题：String s1 = new String("xyz") 创建了几个对象？
        // 答案：1个或2个
        // - 如果常量池中已有"xyz"，则只在堆中创建1个对象
        // - 如果常量池中没有"xyz"，则在常量池和堆中各创建1个对象，共2个
    }

    /**
     * 演示Long.equals()的特殊情况
     *
     * Long.equals()方法要求参数必须是Long类型
     * 传入其他类型（如Integer）会返回false，即使数值相等
     */
    private static void demonstrateLongEqualsIssue() {
        System.out.println("=== Long.equals()特殊情况演示 ===");

        Long longValue = -1L;
        Integer intValue = -1;

        System.out.printf("Long.equals(Integer): %b (类型不同，即使数值相等也返回false)%n", longValue.equals(intValue));
        System.out.printf("Long.equals(Long): %b (类型相同)%n", longValue.equals(Long.valueOf(-1L)));

        System.out.println();
    }
}
