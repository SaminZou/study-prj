package basic.date;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeIntervals {

    public static void main(String[] args) {
        dayIntervals();
        monthIntervals();
    }

    public static void dayIntervals() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        OffsetDateTime now = OffsetDateTime.now();
        String startTime = "1701532800000";
        String endTime = "1701619199000";

        // 昨日
        LocalDateTime preStartDateTime = LocalDateTime.ofEpochSecond(Long.parseLong(startTime) / 1000, 0, now.getOffset())
                                                      .withHour(0)
                                                      .withMinute(0)
                                                      .withSecond(0)
                                                      .withNano(0)
                                                      .minusDays(1);
        LocalDateTime preEndDateTime = LocalDateTime.ofEpochSecond(Long.parseLong(endTime) / 1000, 0, now.getOffset())
                                                    .withHour(23)
                                                    .withMinute(59)
                                                    .withSecond(59)
                                                    .withNano(999_999_999)
                                                    .minusDays(1);
        LocalDateTime preCurrentDateTime = preStartDateTime;
        for (int i = 1; i <= 12; i++) {
            LocalDateTime nextDateTime = preCurrentDateTime.plusHours(2);

            if (nextDateTime.isAfter(preEndDateTime)) {
                nextDateTime = preEndDateTime;
            }

            long startTimestamp = preCurrentDateTime.toEpochSecond(now.getOffset()) * 1000;
            long endTimestamp = nextDateTime.toEpochSecond(now.getOffset()) * 1000 + 999;

            System.out.println("时间段 " + i + ": " + preCurrentDateTime.format(dtf) + " - " + nextDateTime.format(dtf));
            System.out.println("时间段 " + i + ": " + startTimestamp + " - " + endTimestamp);

            nextDateTime = nextDateTime.minusSeconds(1);
            preCurrentDateTime = nextDateTime.plusSeconds(1);
        }

        // 今日
        LocalDateTime startDateTime = LocalDateTime.ofEpochSecond(Long.parseLong(startTime) / 1000, 0, now.getOffset())
                                                   .withHour(0)
                                                   .withMinute(0)
                                                   .withSecond(0)
                                                   .withNano(0);
        LocalDateTime endDateTime = LocalDateTime.ofEpochSecond(Long.parseLong(endTime) / 1000, 0, now.getOffset())
                                                 .withHour(23)
                                                 .withMinute(59)
                                                 .withSecond(59)
                                                 .withNano(999_999_999);
        LocalDateTime currentDateTime = startDateTime;
        for (int i = 1; i <= 12; i++) {
            LocalDateTime nextDateTime = currentDateTime.plusHours(2);

            if (nextDateTime.isAfter(endDateTime)) {
                nextDateTime = endDateTime;
            }

            System.out.println("时间段 " + i + ": " + currentDateTime.format(dtf) + " - " + nextDateTime.format(dtf));
            nextDateTime = nextDateTime.minusSeconds(1);
            currentDateTime = nextDateTime.plusSeconds(1);
        }
    }

    public static void monthIntervals() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        OffsetDateTime now = OffsetDateTime.now();
        // 2023-12-15 23:59:59
        String startTime = "1702655999000";

        System.out.println("上月");
        // 上月
        LocalDateTime preStartDateTime = LocalDateTime.ofEpochSecond(Long.parseLong(startTime) / 1000, 0, now.getOffset())
                                                      .withDayOfMonth(1)
                                                      .withHour(0)
                                                      .withMinute(0)
                                                      .withSecond(0)
                                                      .withNano(0)
                                                      .minusMonths(1);
        LocalDateTime preCurrentDateTime = preStartDateTime;
        while (preCurrentDateTime.getMonth() == preStartDateTime.getMonth()) {
            LocalDateTime preEndTime = LocalDateTime.from(preCurrentDateTime)
                                                    .withHour(23)
                                                    .withMinute(59)
                                                    .withSecond(59)
                                                    .withNano(999_999_999);

            System.out.println("时间: " + preCurrentDateTime.format(dtf));
            System.out.println("时间: " + preEndTime.format(dtf));

            preCurrentDateTime = preCurrentDateTime.plusDays(1);
        }

        System.out.println("本月");
        // 本月
        LocalDateTime indexDateTime = LocalDateTime.ofEpochSecond(Long.parseLong(startTime) / 1000, 0, now.getOffset());
        LocalDateTime startDateTime = LocalDateTime.ofEpochSecond(Long.parseLong(startTime) / 1000, 0, now.getOffset())
                                                   .withDayOfMonth(1)
                                                   .withHour(0)
                                                   .withMinute(0)
                                                   .withSecond(0)
                                                   .withNano(0);
        LocalDateTime currentDateTime = startDateTime;
        while (currentDateTime.getMonth() == startDateTime.getMonth()
                && currentDateTime.getDayOfMonth() <= indexDateTime.getDayOfMonth()) {
            LocalDateTime endTime = LocalDateTime.from(currentDateTime)
                                                 .withHour(23)
                                                 .withMinute(59)
                                                 .withSecond(59)
                                                 .withNano(999_999_999);

            System.out.println("时间: " + currentDateTime.format(dtf));
            System.out.println("时间: " + endTime.format(dtf));

            currentDateTime = currentDateTime.plusDays(1);
        }
    }
}
