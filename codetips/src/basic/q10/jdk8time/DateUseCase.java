package basic.q10.jdk8time;

import java.util.Calendar;
import java.util.Date;

/**
 * JDK8 以前的 Date 类
 *
 * @author samin
 * @date 2023-06-14
 */
public class DateUseCase {

    public static void main(String[] args) {
        Date date = new Date();
        System.out.println("已经过期：" + date.getYear());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        System.out.println("使用 Calendar 封装操作：" + calendar.get(Calendar.YEAR));
    }
}
