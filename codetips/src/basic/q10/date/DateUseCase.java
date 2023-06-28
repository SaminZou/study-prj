package basic.q10.date;

import basic.q10.date.utils.DateUtils;

import java.util.Date;

/**
 * Date 用例
 *
 * @author samin
 * @date 2023-06-28
 */
public class DateUseCase {

    public static void main(String[] args) {
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
