package basic.q10.date;

import basic.q10.date.utils.DateUtils;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 * Date 用例
 *
 * @author samin
 * @date 2023-06-28
 */
public class DateUseCase {

    public static void main(String[] args) throws ParseException {
        System.out.println("当前月份：" + DateUtils.getMonth());

        Date[] dates = DateUtils.getCurMonthPeriod(null);
        System.out.println(Arrays.toString(dates));

        String time1 = "20230907234400";
        String time2 = "20230907234600";
        String time3 = "20230907231300";
        String time4 = "20230907233100";
        String time5 = "20230907231600";
        String time6 = "20230907230100";

        Calendar cal = Calendar.getInstance();
        // 45
        cal.setTime(DateUtils.parseDate(time1));
        System.out.println(DateUtils.correctMinutes(cal.get(Calendar.MINUTE)));
        // 45
        cal.setTime(DateUtils.parseDate(time2));
        System.out.println(DateUtils.correctMinutes(cal.get(Calendar.MINUTE)));
        // 15
        cal.setTime(DateUtils.parseDate(time3));
        System.out.println(DateUtils.correctMinutes(cal.get(Calendar.MINUTE)));
        // 30
        cal.setTime(DateUtils.parseDate(time4));
        System.out.println(DateUtils.correctMinutes(cal.get(Calendar.MINUTE)));
        // 15
        cal.setTime(DateUtils.parseDate(time5));
        System.out.println(DateUtils.correctMinutes(cal.get(Calendar.MINUTE)));
        // 00
        cal.setTime(DateUtils.parseDate(time6));
        System.out.println(DateUtils.correctMinutes(cal.get(Calendar.MINUTE)));

        String time1Str = "20221027170000";
        String time2Str = "20221027171500";
        String time3Str = "20221027173000";
        // true
        System.out.println(DateUtils.isPre15Minute(time1Str, time2Str));
        // true
        System.out.println(DateUtils.isPre15Minute(time2Str, time3Str));
        // false
        System.out.println(DateUtils.isPre15Minute(time1Str, time3Str));

        Date date = new Date();
        System.out.println("已经过期的方法：" + DateUtils.getDateYear(date));
        System.out.println("使用 Calendar 封装操作：" + DateUtils.getDateYearV2(date));

        // 前 5 天
        System.out.println(DateUtils.getPreviousDays(new Date(), 5));
        // 前 5 个工作日
        System.out.println(DateUtils.getPreviousDays(new Date(), 5, false));
        // 前 5 个假期
        System.out.println(DateUtils.getPreviousDays(new Date(), 5, true));
    }
}
