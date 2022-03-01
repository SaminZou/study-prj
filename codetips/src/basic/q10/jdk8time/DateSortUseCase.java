package basic.q10.jdk8time;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 时间列表排序
 *
 * @author samin
 * @date 2021-10-22
 */
public class DateSortUseCase {

    public static void main(String[] args) {
        String date1 = "2018-10-21 10:11:12";
        String date2 = "2021-10-23 11:11:12";
        String date3 = "2021-10-20 09:11:12";
        String date4 = "2020-10-22 12:11:12";

        List<SortObj> list = new ArrayList<>();
        list.add(new SortObj(1, "foo1",
                OffsetDateTime.of(LocalDateTime.parse(date1, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                        ZoneOffset.of(DateTimeUseCase.OFFSET_ID))));
        list.add(new SortObj(2, "foo2",
                OffsetDateTime.of(LocalDateTime.parse(date2, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                        ZoneOffset.of(DateTimeUseCase.OFFSET_ID))));
        list.add(new SortObj(3, "foo3",
                OffsetDateTime.of(LocalDateTime.parse(date3, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                        ZoneOffset.of(DateTimeUseCase.OFFSET_ID))));
        list.add(new SortObj(4, "foo4",
                OffsetDateTime.of(LocalDateTime.parse(date4, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                        ZoneOffset.of(DateTimeUseCase.OFFSET_ID))));

        // 1 2 3 4
        System.out.println(list);

        // 2 3 4 1
        Collections.sort(list);
        System.out.println(list);

        // 时间排序
        List<OffsetDateTime> dateList = new ArrayList<>();
        dateList.add(OffsetDateTime.of(LocalDateTime.parse(date1, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                ZoneOffset.of(DateTimeUseCase.OFFSET_ID)));
        dateList.add(OffsetDateTime.of(LocalDateTime.parse(date2, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                ZoneOffset.of(DateTimeUseCase.OFFSET_ID)));
        dateList.add(OffsetDateTime.of(LocalDateTime.parse(date3, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                ZoneOffset.of(DateTimeUseCase.OFFSET_ID)));
        dateList.add(OffsetDateTime.of(LocalDateTime.parse(date4, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                ZoneOffset.of(DateTimeUseCase.OFFSET_ID)));

        System.out.println(dateList);

        System.out.println(dateList.stream().sorted(Comparator.comparing(e -> e)).collect(Collectors.toList()));
    }

    private static class SortObj implements Comparable<SortObj> {

        private Integer foo;
        private String bar;
        private OffsetDateTime time;

        public OffsetDateTime getTime() {
            return time;
        }

        public SortObj(Integer foo, String bar, OffsetDateTime time) {
            this.foo = foo;
            this.bar = bar;
            this.time = time;
        }

        @Override
        public String toString() {
            return "ObjWithDate{" + "foo=" + foo + ", bar='" + bar + '\'' + ", time=" + time + '}' + "\n";
        }

        @Override
        public int compareTo(SortObj o) {
            return -this.getTime().compareTo(o.getTime());
        }
    }
}
