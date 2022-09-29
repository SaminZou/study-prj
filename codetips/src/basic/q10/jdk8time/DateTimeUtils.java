package basic.q10.jdk8time;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 时间工具类
 *
 * @author samin
 * @date 2022-09-29
 */
public class DateTimeUtils {

    public static void main(String[] args) {
        // 前 5 个工作日
        System.out.println(getPreviousDays(new Date(), 5, false));
        // 前 5 个假期
        System.out.println(getPreviousDays(new Date(), 5, true));
    }

    /**
     * 获取前 n 工作日 / 假期
     *
     * @param currentDate 当前时间
     * @param n           需要获取的天数
     * @param isHoliday   是否假期
     * @return 前 n 个工作日 / 假期数组
     */
    public static List<Date> getPreviousDays(Date currentDate, int n, boolean isHoliday) {
        List<Date> results = new ArrayList<>();

        LocalDateTime currentLocalDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        while (results.size() < n) {
            DayOfWeek dayOfWeek = DayOfWeek.of(currentLocalDateTime.get(ChronoField.DAY_OF_WEEK));
            LocalDateTime tempDateTime;

            if (isHoliday) {
                switch (dayOfWeek) {
                    case TUESDAY:
                        tempDateTime = currentLocalDateTime.minus(2, ChronoUnit.DAYS);
                        break;
                    case WEDNESDAY:
                        tempDateTime = currentLocalDateTime.minus(3, ChronoUnit.DAYS);
                        break;
                    case THURSDAY:
                        tempDateTime = currentLocalDateTime.minus(4, ChronoUnit.DAYS);
                        break;
                    case FRIDAY:
                        tempDateTime = currentLocalDateTime.minus(5, ChronoUnit.DAYS);
                        break;
                    case SATURDAY:
                        tempDateTime = currentLocalDateTime.minus(6, ChronoUnit.DAYS);
                        break;
                    default:
                        tempDateTime = currentLocalDateTime.minus(1, ChronoUnit.DAYS);
                }
                results.add(Date.from(tempDateTime.atZone(ZoneId.systemDefault()).toInstant()));
                currentLocalDateTime = tempDateTime;
            } else {
                switch (dayOfWeek) {
                    case MONDAY:
                        tempDateTime = currentLocalDateTime.minus(3, ChronoUnit.DAYS);
                        break;
                    case SUNDAY:
                        tempDateTime = currentLocalDateTime.minus(2, ChronoUnit.DAYS);
                        break;
                    default:
                        tempDateTime = currentLocalDateTime.minus(1, ChronoUnit.DAYS);
                }
                results.add(Date.from(tempDateTime.atZone(ZoneId.systemDefault()).toInstant()));
                currentLocalDateTime = tempDateTime;
            }
        }

        return results;
    }
}
