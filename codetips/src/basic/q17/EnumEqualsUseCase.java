package basic.q17;

/**
 * 枚举类判断相等的方法
 *
 * @author samin
 * @date 2021-06-01
 */
public class EnumEqualsUseCase {

    public static void main(String[] args) {

        WeekEnum weekEnum = WeekEnum.parseValue(5);

        System.out.println(weekEnum.equals(WeekEnum.FRIDAY));
        System.out.println(weekEnum == WeekEnum.FRIDAY);

        // 可以看到以上两个输出结果是一样的，原因是 java.lang.Enum 中的 equals 方法被重写，使用的就是 ==
        // public final boolean equals(Object other) { return this==other; }
    }
}
