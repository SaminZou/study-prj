package basic.q17;

/**
 * 枚举类 + switch 最佳实践
 *
 * @author samin
 * @date 2021-03-17
 */
public class EnumSwitchUseCase {

    /** 先解析是哪个枚举类，在进行 switch 逻辑判断 */
    public static void parseWeek(int value) {
        WeekEnum weekEnum = WeekEnum.parseValue(value);

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

    public static void main(String[] args) {
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
}
