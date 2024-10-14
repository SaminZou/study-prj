package basic;

/**
 * 枚举类事例
 *
 * @author samin
 * @date 2021-06-01
 */
public class EnumEqualsUseCase {

    public static void main(String[] args) {
        // Part 1: 枚举类判断相等的方法
        WeekEnum weekEnum = WeekEnum.badTaste(5);

        System.out.println(weekEnum.equals(WeekEnum.FRIDAY));
        System.out.println(weekEnum == WeekEnum.FRIDAY);

        System.out.println(WeekEnum.bestPractice(5));

        // 可以看到以上两个输出结果是一样的，原因是 java.lang.Enum 中的 equals 方法被重写，使用的就是 ==
        // public final boolean equals(Object other) { return this==other; }

        // Part 2: 枚举类 + switch 最佳实践
        int unknown = 999;
        int monday = 1;
        int tuesday = 2;
        int wednesday = 3;
        int thursday = 4;
        int friday = 5;
        int saturday = 6;
        int sunday = 7;

        parseWeek(unknown);
        parseWeek(monday);
        parseWeek(tuesday);
        parseWeek(wednesday);
        parseWeek(thursday);
        parseWeek(friday);
        parseWeek(saturday);
        parseWeek(sunday);
    }

    /**
     * 先解析是哪个枚举类，在进行 switch 逻辑判断
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

        WeekEnum(int value, String label) {
            this.value = value;
            this.label = label;
        }

        public String getLabel() {
            return label;
        }

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

        public static WeekEnum bestPractice(int value) {
            for (WeekEnum weekEnum : values()) {
                if (weekEnum.value == value) {
                    return weekEnum;
                }
            }
            return UNKNOWN;
        }
    }
}
