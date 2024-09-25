package basic.q16;

/**
 * 星期枚举类
 *
 * @author samin
 * @date 2021-03-17
 */
public enum WeekEnum {

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
