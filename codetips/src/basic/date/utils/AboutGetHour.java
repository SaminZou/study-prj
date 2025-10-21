package basic.date.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * LocalDateTime getHour
 * <p>
 * Description: LocalDateTime getHour
 * <p>
 * Created By: Samin Created Date: 2025-10-20
 */
public class AboutGetHour {

    public static void main(String[] args) {
        String time1 = "2025-10-19 23:41:00";
        String time2 = "2025-10-20 00:12:00";
        String time3 = "2025-10-20 01:13:00";
        String time4 = "2025-10-20 02:13:00";

        // time1 ~ time4 to LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime1 = LocalDateTime.parse(time1, formatter);
        LocalDateTime dateTime2 = LocalDateTime.parse(time2, formatter);
        LocalDateTime dateTime3 = LocalDateTime.parse(time3, formatter);
        LocalDateTime dateTime4 = LocalDateTime.parse(time4, formatter);

        // 获取的是当前小时数，不存在偏移
        String output = String.format("%s ~ %s ~ %s ~ %s", dateTime1.getHour(), dateTime2.getHour(), dateTime3.getHour(),
                                      dateTime4.getHour());
        System.out.println(output);
    }
}
