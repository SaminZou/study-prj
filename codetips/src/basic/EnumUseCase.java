package basic;

/**
 * 枚举类事例
 *
 * @author samin
 * @date 2021-06-01
 */
public class EnumUseCase {

    public static void main(String[] args) {
        // Part 1: 枚举类判断相等的方法
        WeekEnum weekEnum = WeekEnum.badTaste(5);

        System.out.println("equals() vs == 比较结果:");
        System.out.println("equals(): " + weekEnum.equals(WeekEnum.FRIDAY));
        System.out.println("==: " + (weekEnum == WeekEnum.FRIDAY));

        System.out.println("\n不同查找方法的结果:");
        System.out.println("badTaste(5): " + WeekEnum.badTaste(5));
        System.out.println("bestPractice(5): " + WeekEnum.bestPractice(5));
        System.out.println("optimizedLookup(5): " + WeekEnum.optimizedLookup(5));
        System.out.println("safeLookup(null): " + WeekEnum.safeLookup(null));

        // 可以看到以上两个输出结果是一样的，原因是 java.lang.Enum 中的 equals 方法被重写，使用的就是 ==
        // public final boolean equals(Object other) { return this==other; }

        // Part 2: 枚举类 + switch 最佳实践
        System.out.println("\n=== 原始 parseWeek 方法 ===");
        int[] testValues = {999, 1, 2, 3, 4, 5, 6, 7};
        for (int value : testValues) {
            parseWeek(value);
        }

        System.out.println("\n=== 优化后的 parseWeekOptimized 方法 ===");
        for (int value : testValues) {
            parseWeekOptimized(value);
        }

        System.out.println("\n=== 使用枚举自身逻辑的 parseWeekWithEnumLogic 方法 ===");
        for (int value : testValues) {
            parseWeekWithEnumLogic(value);
        }

        // 性能对比演示
        System.out.println("\n=== 性能对比 ===");
        demonstratePerformance();
    }

    /**
     * 性能对比演示
     */
    private static void demonstratePerformance() {
        int iterations = 1000000;
        int testValue = 5;

        // 预热
        for (int i = 0; i < 1000; i++) {
            WeekEnum.bestPractice(testValue);
            WeekEnum.optimizedLookup(testValue);
        }

        // 测试 O(n) 方法
        long startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            WeekEnum.bestPractice(testValue);
        }
        long bestPracticeTime = System.nanoTime() - startTime;

        // 测试 O(1) 方法
        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            WeekEnum.optimizedLookup(testValue);
        }
        long optimizedTime = System.nanoTime() - startTime;

        System.out.printf("bestPractice (O(n)): %d ns%n", bestPracticeTime);
        System.out.printf("optimizedLookup (O(1)): %d ns%n", optimizedTime);
        System.out.printf("性能提升: %.2fx%n", (double) bestPracticeTime / optimizedTime);
    }

    /**
     * 原始方法 - 使用冗长的 switch 语句
     */
    public static void parseWeek(int value) {
        WeekEnum weekEnum = WeekEnum.badTaste(value);

        switch (weekEnum) {
            case MONDAY:
                System.out.println("执行周一的逻辑，输出解析内容：" + weekEnum.getLabel());
                break;
            case TUESDAY:
                System.out.println("执行周二的逻辑，输出解析内容：" + weekEnum.getLabel());
                break;
            case WEDNESDAY:
                System.out.println("执行周三的逻辑，输出解析内容：" + weekEnum.getLabel());
                break;
            case THURSDAY:
                System.out.println("执行周四的逻辑，输出解析内容：" + weekEnum.getLabel());
                break;
            case FRIDAY:
                System.out.println("执行周五的逻辑，输出解析内容：" + weekEnum.getLabel());
                break;
            case SATURDAY:
                System.out.println("执行周六的逻辑，输出解析内容：" + weekEnum.getLabel());
                break;
            case SUNDAY:
                System.out.println("执行周日的逻辑，输出解析内容：" + weekEnum.getLabel());
                break;
            default:
                System.out.println("无法解析的内容，输出解析内容：" + weekEnum.getLabel());
        }
    }

    /**
     * 优化方法 - 消除重复代码，使用 StringBuilder 提高性能
     */
    public static void parseWeekOptimized(int value) {
        WeekEnum weekEnum = WeekEnum.optimizedLookup(value);
        String message = String.format("执行%s的逻辑，输出解析内容：%s", 
            weekEnum.name().charAt(0) + weekEnum.name().substring(1).toLowerCase(), 
            weekEnum.getLabel());
        System.out.println(message);
    }

    /**
     * 进一步优化 - 使用枚举自身的业务逻辑方法
     */
    public static void parseWeekWithEnumLogic(int value) {
        WeekEnum weekEnum = WeekEnum.safeLookup(value);
        weekEnum.executeDayLogic();
    }

    enum WeekEnum {

        /**
         * 未知
         */
        UNKNOWN(-1, "未知"),

        /**
         * 星期一
         */
        MONDAY(1, "星期一"),

        /**
         * 星期二
         */
        TUESDAY(2, "星期二"),

        /**
         * 星期三
         */
        WEDNESDAY(3, "星期三"),

        /**
         * 星期四
         */
        THURSDAY(4, "星期四"),

        /**
         * 星期五
         */
        FRIDAY(5, "星期五"),

        /**
         * 星期六
         */
        SATURDAY(6, "星期六"),

        /**
         * 星期日
         */
        SUNDAY(7, "星期日"),
        ;

        private final int value;
        private final String label;

        // O(1) lookup optimization using static Map
        private static final java.util.Map<Integer, WeekEnum> VALUE_TO_ENUM_MAP = new java.util.HashMap<>();

        static {
            for (WeekEnum weekEnum : values()) {
                VALUE_TO_ENUM_MAP.put(weekEnum.value, weekEnum);
            }
        }

        WeekEnum(int value, String label) {
            this.value = value;
            this.label = label;
        }

        public String getLabel() {
            return label;
        }

        public int getValue() {
            return value;
        }

        /**
         * Bad practice - uses verbose switch statement
         */
        public static WeekEnum badTaste(int value) {
            WeekEnum result;

            switch (value) {
                case 1:
                    result = MONDAY;
                    break;
                case 2:
                    result = TUESDAY;
                    break;
                case 3:
                    result = WEDNESDAY;
                    break;
                case 4:
                    result = THURSDAY;
                    break;
                case 5:
                    result = FRIDAY;
                    break;
                case 6:
                    result = SATURDAY;
                    break;
                case 7:
                    result = SUNDAY;
                    break;
                default:
                    result = UNKNOWN;
            }

            return result;
        }

        /**
         * O(n) lookup - iterates through all enum values
         */
        public static WeekEnum bestPractice(int value) {
            for (WeekEnum weekEnum : values()) {
                if (weekEnum.value == value) {
                    return weekEnum;
                }
            }
            return UNKNOWN;
        }

        /**
         * O(1) optimized lookup using Map - best performance
         */
        public static WeekEnum optimizedLookup(int value) {
            return VALUE_TO_ENUM_MAP.getOrDefault(value, UNKNOWN);
        }

        /**
         * Safe lookup with null validation
         */
        public static WeekEnum safeLookup(Integer value) {
            return value == null ? UNKNOWN : optimizedLookup(value);
        }

        /**
         * 枚举自身执行业务逻辑的方法 - 体现面向对象设计
         */
        public void executeDayLogic() {
            String message;
            if (this == UNKNOWN) {
                message = "无法解析的内容，输出解析内容：" + this.getLabel();
            } else {
                message = String.format("执行%s的逻辑，输出解析内容：%s", 
                    this.name().charAt(0) + this.name().substring(1).toLowerCase(), 
                    this.getLabel());
            }
            System.out.println(message);
        }

        /**
         * 判断是否为工作日
         */
        public boolean isWeekday() {
            return this == MONDAY || this == TUESDAY || this == WEDNESDAY || 
                   this == THURSDAY || this == FRIDAY;
        }

        /**
         * 判断是否为周末
         */
        public boolean isWeekend() {
            return this == SATURDAY || this == SUNDAY;
        }

        /**
         * 获取下一天的枚举
         */
        public WeekEnum nextDay() {
            switch (this) {
                case MONDAY: return TUESDAY;
                case TUESDAY: return WEDNESDAY;
                case WEDNESDAY: return THURSDAY;
                case THURSDAY: return FRIDAY;
                case FRIDAY: return SATURDAY;
                case SATURDAY: return SUNDAY;
                case SUNDAY: return MONDAY;
                default: return UNKNOWN;
            }
        }
    }
}
