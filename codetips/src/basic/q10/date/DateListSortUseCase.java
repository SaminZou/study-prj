package basic.q10.date;

import basic.q10.date.utils.DateTimeUtils;

import java.time.OffsetDateTime;
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
public class DateListSortUseCase {

    public static void main(String[] args) {
        String date1 = "2018-10-21 10:11:12";
        String date2 = "2021-10-23 11:11:12";
        String date3 = "2021-10-20 09:11:12";
        String date4 = "2020-10-22 12:11:12";

        List<SortObj> list = new ArrayList<>();
        list.add(new SortObj(1, "foo1", DateTimeUtils.stringToOffsetDateTime(date1)));
        list.add(new SortObj(2, "foo2", DateTimeUtils.stringToOffsetDateTime(date2)));
        list.add(new SortObj(3, "foo3", DateTimeUtils.stringToOffsetDateTime(date3)));
        list.add(new SortObj(4, "foo4", DateTimeUtils.stringToOffsetDateTime(date4)));

        // 1 2 3 4
        System.out.println(list);

        // sort
        Collections.sort(list);

        // 2 3 4 1
        System.out.println(list);

        // 时间排序
        List<OffsetDateTime> dateList = new ArrayList<>();
        dateList.add(DateTimeUtils.stringToOffsetDateTime(date1));
        dateList.add(DateTimeUtils.stringToOffsetDateTime(date2));
        dateList.add(DateTimeUtils.stringToOffsetDateTime(date3));
        dateList.add(DateTimeUtils.stringToOffsetDateTime(date4));

        System.out.println(dateList);

        System.out.println(dateList.stream().sorted(Comparator.comparing(e -> e)).collect(Collectors.toList()));
    }

    /**
     * 需要实现排序接口
     */
    private static class SortObj implements Comparable<SortObj> {

        private final Integer foo;
        private final String bar;
        private final OffsetDateTime time;

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
